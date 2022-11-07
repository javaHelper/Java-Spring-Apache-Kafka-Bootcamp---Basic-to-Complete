"use strict";
/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2022 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.sanitizeTypeScriptESLintRule = void 0;
/**
 * Sanitizes a TypeScript ESLint rule
 *
 * TypeScript ESLint rules that relies on TypeScript's type system unconditionally assumes
 * that the type checker is always available. Linting a source code with such rules could
 * lead to a runtime error if that assumption turned out to be wrong for whatever reason.
 *
 * Aa TypeScript ESLint rule needs, therefore, to be sanitized in case its implementation
 * relies on type checking. The metadata of such a rule sets the `requiresTypeChecking`
 * property to `true`.
 *
 * The sanitization of a rule is nothing more than a decoration of its implementation. It
 * determining whether the rule uses type checking and checks whether type information is
 * available at runtime. If so, the execution of the rule proceeds; otherwise, it stops.
 *
 * @param rule a TypeScript ESLint rule to sanitize
 * @returns the sanitized rule
 */
function sanitizeTypeScriptESLintRule(rule) {
    return {
        ...(!!rule.meta && { meta: rule.meta }),
        create(originalContext) {
            var _a;
            const interceptingContext = {
                id: originalContext.id,
                options: originalContext.options,
                settings: originalContext.settings,
                parserPath: originalContext.parserPath,
                parserOptions: originalContext.parserOptions,
                parserServices: originalContext.parserServices,
                getCwd() {
                    return originalContext.getCwd();
                },
                getPhysicalFilename() {
                    return originalContext.getPhysicalFilename();
                },
                getAncestors() {
                    return originalContext.getAncestors();
                },
                getDeclaredVariables(node) {
                    return originalContext.getDeclaredVariables(node);
                },
                getFilename() {
                    return originalContext.getFilename();
                },
                getScope() {
                    return originalContext.getScope();
                },
                getSourceCode() {
                    return originalContext.getSourceCode();
                },
                markVariableAsUsed(name) {
                    return originalContext.markVariableAsUsed(name);
                },
                report(descriptor) {
                    return originalContext.report(descriptor);
                },
            };
            /**
             * Overrides the rule behaviour if it requires TypeScript's type checker
             * but type information is missing.
             */
            if (((_a = rule.meta) === null || _a === void 0 ? void 0 : _a.docs) &&
                rule.meta.docs.requiresTypeChecking === true &&
                interceptingContext.parserServices.hasFullTypeInformation !== true) {
                return {};
            }
            return rule.create(interceptingContext);
        },
    };
}
exports.sanitizeTypeScriptESLintRule = sanitizeTypeScriptESLintRule;
//# sourceMappingURL=sanitize.js.map