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
exports.rule = void 0;
const detectReactSelector = [
    ':matches(',
    [
        'CallExpression[callee.name="require"][arguments.0.value="react"]',
        'CallExpression[callee.name="require"][arguments.0.value="create-react-class"]',
        'ImportDeclaration[source.value="react"]',
    ].join(','),
    ')',
].join('');
exports.rule = {
    meta: {
        messages: {
            reactDetected: 'React detected',
        },
    },
    create(context) {
        return {
            [detectReactSelector](node) {
                context.report({
                    messageId: 'reactDetected',
                    node,
                });
            },
        };
    },
};
//# sourceMappingURL=rule-detect-react.js.map