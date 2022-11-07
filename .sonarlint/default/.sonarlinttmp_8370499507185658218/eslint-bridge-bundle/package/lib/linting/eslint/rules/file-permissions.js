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
// https://sonarsource.github.io/rspec/#/rspec/S2612/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("./helpers");
const chmodLikeFunction = ['chmod', 'chmodSync', 'fchmod', 'fchmodSync', 'lchmod', 'lchmodSync'];
exports.rule = {
    meta: {
        messages: {
            safePermission: 'Make sure this permission is safe.',
        },
    },
    create(context) {
        function isChmodLikeFunction(node) {
            const { callee } = node;
            if (callee.type !== 'MemberExpression') {
                return false;
            }
            // to support fs promises we are only checking the name of the function
            return (0, helpers_1.isIdentifier)(callee.property, ...chmodLikeFunction);
        }
        function modeFromLiteral(modeExpr) {
            const modeValue = modeExpr.value;
            let mode = null;
            if (typeof modeValue === 'string') {
                mode = Number.parseInt(modeValue, 8);
            }
            else if (typeof modeValue === 'number') {
                const raw = modeExpr.raw;
                // ts parser interprets number starting with 0 as decimal, we need to parse it as octal value
                if (raw && raw.startsWith('0') && !raw.startsWith('0o')) {
                    mode = Number.parseInt(raw, 8);
                }
                else {
                    mode = modeValue;
                }
            }
            return mode;
        }
        // fs.constants have these value only when running on linux, we need to hardcode them to be able to test on win
        const FS_CONST = {
            S_IRWXU: 0o700,
            S_IRUSR: 0o400,
            S_IWUSR: 0o200,
            S_IXUSR: 0o100,
            S_IRWXG: 0o70,
            S_IRGRP: 0o40,
            S_IWGRP: 0o20,
            S_IXGRP: 0o10,
            S_IRWXO: 0o7,
            S_IROTH: 0o4,
            S_IWOTH: 0o2,
            S_IXOTH: 0o1,
        };
        function modeFromMemberExpression(modeExpr) {
            const { object, property } = modeExpr;
            if ((0, helpers_1.isMemberExpression)(object, 'fs', 'constants') && property.type === 'Identifier') {
                return FS_CONST[property.name];
            }
            return null;
        }
        function modeFromExpression(expr, visited) {
            if (!expr) {
                return null;
            }
            if (expr.type === 'MemberExpression') {
                return modeFromMemberExpression(expr);
            }
            else if (expr.type === 'Literal') {
                return modeFromLiteral(expr);
            }
            else if (expr.type === 'Identifier') {
                const usage = (0, helpers_1.getUniqueWriteUsage)(context, expr.name);
                if (usage && !visited.has(usage)) {
                    visited.add(usage);
                    return modeFromExpression(usage, visited);
                }
            }
            else if (expr.type === 'BinaryExpression') {
                const { left, operator, right } = expr;
                if (operator === '|') {
                    const leftValue = modeFromExpression(left, visited);
                    const rightValue = modeFromExpression(right, visited);
                    if (leftValue && rightValue) {
                        return leftValue | rightValue;
                    }
                }
            }
            return null;
        }
        function checkModeArgument(node, moduloTest) {
            const visited = new Set();
            const mode = modeFromExpression(node, visited);
            if (mode !== null && !isNaN(mode) && mode % 8 !== moduloTest) {
                context.report({
                    node,
                    messageId: 'safePermission',
                });
            }
        }
        return {
            CallExpression: (node) => {
                const callExpression = node;
                if (isChmodLikeFunction(callExpression)) {
                    checkModeArgument(callExpression.arguments[0], 0);
                    checkModeArgument(callExpression.arguments[1], 0);
                }
                else if ((0, helpers_1.isCallToFQN)(context, callExpression, 'process', 'umask')) {
                    checkModeArgument(callExpression.arguments[0], 7);
                }
            },
        };
    },
};
//# sourceMappingURL=file-permissions.js.map