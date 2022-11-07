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
exports.getRuleSchema = void 0;
const helpers_1 = require("helpers");
/**
 * Extracts the schema of a rule
 * @param ruleModule the rule definition
 * @param ruleId the rule id
 * @returns the extracted rule schema, if any
 */
function getRuleSchema(ruleModule, ruleId) {
    if (!ruleModule) {
        (0, helpers_1.debug)(`ruleModule not found for rule ${ruleId}`);
        return undefined;
    }
    if (!ruleModule.meta || !ruleModule.meta.schema) {
        return undefined;
    }
    const { schema } = ruleModule.meta;
    return Array.isArray(schema) ? schema : [schema];
}
exports.getRuleSchema = getRuleSchema;
//# sourceMappingURL=schema.js.map