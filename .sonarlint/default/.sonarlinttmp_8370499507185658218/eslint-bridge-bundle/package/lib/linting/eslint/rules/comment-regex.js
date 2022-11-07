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
// https://sonarsource.github.io/rspec/#/rspec/S124/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
exports.rule = {
    meta: {
        schema: [
            {
                type: 'object',
                properties: {
                    regularExpression: {
                        type: 'string',
                    },
                    message: {
                        type: 'string',
                    },
                    flags: {
                        type: 'string',
                    },
                },
                additionalProperties: false,
            },
        ],
    },
    create(context) {
        const options = context.options[0] || {};
        const flags = options.flags || '';
        const cleanedFlags = 'gimusy'
            .split('')
            .filter(c => flags.includes(c))
            .join('');
        const pattern = options.regularExpression
            ? new RegExp(options.regularExpression, cleanedFlags)
            : undefined;
        const message = options.message || 'The regular expression matches this comment.';
        return {
            'Program:exit': () => {
                context.getSourceCode().getAllComments().forEach(comment => {
                    const rawTextTrimmed = comment.value.trim();
                    if (pattern && pattern.test(rawTextTrimmed)) {
                        context.report({
                            message,
                            loc: comment.loc,
                        });
                    }
                });
            },
        };
    },
};
//# sourceMappingURL=comment-regex.js.map