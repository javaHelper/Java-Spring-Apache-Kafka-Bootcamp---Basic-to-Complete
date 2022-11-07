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
// https://sonarsource.github.io/rspec/#/rspec/S3403/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("./helpers");
const parameters_1 = require("linting/eslint/linter/parameters");
exports.rule = {
    meta: {
        hasSuggestions: true,
        schema: [
            {
                // internal parameter for rules having secondary locations
                enum: [parameters_1.SONAR_RUNTIME],
            },
        ],
    },
    create(context) {
        const services = context.parserServices;
        if (!(0, helpers_1.isRequiredParserServices)(services)) {
            return {};
        }
        function isComparableTo(lhs, rhs) {
            const checker = services.program.getTypeChecker();
            const lhsType = checker.getBaseTypeOfLiteralType((0, helpers_1.getTypeFromTreeNode)(lhs, services));
            const rhsType = checker.getBaseTypeOfLiteralType((0, helpers_1.getTypeFromTreeNode)(rhs, services));
            // @ts-ignore private API
            return (checker.isTypeAssignableTo(lhsType, rhsType) || checker.isTypeAssignableTo(rhsType, lhsType));
        }
        return {
            BinaryExpression: (node) => {
                const { left, operator, right } = node;
                if (['===', '!=='].includes(operator) && !isComparableTo(left, right)) {
                    const [actual, expected, outcome] = operator === '===' ? ['===', '==', 'false'] : ['!==', '!=', 'true'];
                    const operatorToken = context
                        .getSourceCode()
                        .getTokensBetween(left, right)
                        .find(token => token.type === 'Punctuator' && token.value === operator);
                    context.report({
                        message: (0, helpers_1.toEncodedMessage)(`Remove this "${actual}" check; it will always be ${outcome}. Did you mean to use "${expected}"?`, [left, right]),
                        loc: operatorToken.loc,
                        suggest: [
                            {
                                desc: `Replace "${actual}" with "${expected}"`,
                                fix: fixer => fixer.replaceText(operatorToken, expected),
                            },
                        ],
                    });
                }
            },
        };
    },
};
//# sourceMappingURL=different-types-comparison.js.map