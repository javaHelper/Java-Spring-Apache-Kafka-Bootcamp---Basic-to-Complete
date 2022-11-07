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
// https://sonarsource.github.io/rspec/#/rspec/S5542/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const helpers_1 = require("./helpers");
const aliases = [
    'AES128',
    'AES192',
    'AES256',
    'BF',
    'blowfish',
    'CAMELLIA128',
    'CAMELLIA192',
    'CAMELLIA256',
    'CAST',
    'DES',
    'DES-EDE',
    'DES-EDE3',
    'DES3',
    'DESX',
    'RC2',
    'RC2-40',
    'RC2-64',
    'RC2-128',
    'SEED',
];
exports.rule = {
    meta: {
        messages: {
            useSecureMode: 'Use a secure mode and padding scheme.',
        },
    },
    create(context) {
        const patterns = [new RegExp('CBC', 'i'), new RegExp('ECB', 'i')];
        aliases.forEach(alias => patterns.push(new RegExp(`^${alias}$`, 'i')));
        return {
            CallExpression: (node) => {
                const callExpression = node;
                if (!(0, helpers_1.isCallToFQN)(context, callExpression, 'crypto', 'createCipheriv')) {
                    return;
                }
                const sensitiveArgument = callExpression.arguments[0];
                const sensitiveArgumentValue = (0, helpers_1.getValueOfExpression)(context, sensitiveArgument, 'Literal');
                if (!sensitiveArgumentValue) {
                    return;
                }
                const { value } = sensitiveArgumentValue;
                if (typeof value !== 'string') {
                    return;
                }
                if (patterns.some(pattern => pattern.test(value))) {
                    context.report({
                        messageId: 'useSecureMode',
                        node: sensitiveArgument,
                    });
                }
            },
        };
    },
};
//# sourceMappingURL=encryption-secure-mode.js.map