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
// https://sonarsource.github.io/rspec/#/rspec/S6353/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const regex_1 = require("./helpers/regex");
exports.rule = (0, regex_1.createRegExpRule)(context => {
    let flags;
    return {
        onRegExpLiteralEnter: (node) => {
            ({ flags } = node);
        },
        onCharacterClassEnter: (node) => {
            checkBulkyAnyCharacterClass(node, flags, context);
            checkBulkyNumericCharacterClass(node, context);
            checkBulkyAlphaNumericCharacterClass(node, context);
        },
        onQuantifierEnter: (node) => {
            checkBulkyQuantifier(node, context);
        },
    };
});
function checkBulkyAnyCharacterClass(node, flags, context) {
    if (node.negate || node.elements.length !== 2) {
        return;
    }
    let hasLowerEscapeW = false;
    let hasUpperEscapeW = false;
    let hasLowerEscapeD = false;
    let hasUpperEscapeD = false;
    let hasLowerEscapeS = false;
    let hasUpperEscapeS = false;
    node.elements.forEach(element => {
        hasLowerEscapeW || (hasLowerEscapeW = element.type === 'CharacterSet' && element.kind === 'word' && !element.negate);
        hasUpperEscapeW || (hasUpperEscapeW = element.type === 'CharacterSet' && element.kind === 'word' && element.negate);
        hasLowerEscapeD || (hasLowerEscapeD = element.type === 'CharacterSet' && element.kind === 'digit' && !element.negate);
        hasUpperEscapeD || (hasUpperEscapeD = element.type === 'CharacterSet' && element.kind === 'digit' && element.negate);
        hasLowerEscapeS || (hasLowerEscapeS = element.type === 'CharacterSet' && element.kind === 'space' && !element.negate);
        hasUpperEscapeS || (hasUpperEscapeS = element.type === 'CharacterSet' && element.kind === 'space' && element.negate);
    });
    const isBulkyAnyCharacterClass = (hasLowerEscapeW && hasUpperEscapeW) ||
        (hasLowerEscapeD && hasUpperEscapeD) ||
        (hasLowerEscapeS && hasUpperEscapeS && flags.dotAll);
    if (isBulkyAnyCharacterClass) {
        context.reportRegExpNode({
            message: `Use concise character class syntax '.' instead of '${node.raw}'.`,
            node: context.node,
            regexpNode: node,
        });
    }
}
function checkBulkyNumericCharacterClass(node, context) {
    if (node.elements.length === 1) {
        const [element] = node.elements;
        const hasDigit = element.type === 'CharacterClassRange' && element.raw === '0-9';
        if (hasDigit) {
            const expected = node.negate ? '\\D' : '\\d';
            const actual = node.raw;
            context.reportRegExpNode({
                message: `Use concise character class syntax '${expected}' instead of '${actual}'.`,
                node: context.node,
                regexpNode: node,
            });
        }
    }
}
function checkBulkyAlphaNumericCharacterClass(node, context) {
    if (node.elements.length === 4) {
        let hasDigit = false, hasLowerCase = false, hasUpperCase = false, hasUnderscore = false;
        for (const element of node.elements) {
            hasDigit || (hasDigit = element.type === 'CharacterClassRange' && element.raw === '0-9');
            hasLowerCase || (hasLowerCase = element.type === 'CharacterClassRange' && element.raw === 'a-z');
            hasUpperCase || (hasUpperCase = element.type === 'CharacterClassRange' && element.raw === 'A-Z');
            hasUnderscore || (hasUnderscore = element.type === 'Character' && element.raw === '_');
        }
        if (hasDigit && hasLowerCase && hasUpperCase && hasUnderscore) {
            const expected = node.negate ? '\\W' : '\\w';
            const actual = node.raw;
            context.reportRegExpNode({
                message: `Use concise character class syntax '${expected}' instead of '${actual}'.`,
                node: context.node,
                regexpNode: node,
            });
        }
    }
}
function checkBulkyQuantifier(node, context) {
    const { raw } = node;
    let bulkyQuantifier;
    if (/\{0,1\}\??$/.test(raw)) {
        bulkyQuantifier = { concise: '?', verbose: '{0,1}' };
    }
    else if (/\{0,\}\??$/.test(raw)) {
        bulkyQuantifier = { concise: '*', verbose: '{0,}' };
    }
    else if (/\{1,\}\??$/.test(raw)) {
        bulkyQuantifier = { concise: '+', verbose: '{1,}' };
    }
    else if (/\{(\d+),\1\}\??$/.test(raw)) {
        bulkyQuantifier = { concise: `{${node.min}}`, verbose: `{${node.min},${node.min}}` };
    }
    if (bulkyQuantifier) {
        context.reportRegExpNode({
            message: `Use concise quantifier syntax '${bulkyQuantifier.concise}' instead of '${bulkyQuantifier.verbose}'.`,
            node: context.node,
            regexpNode: node,
        });
    }
}
//# sourceMappingURL=concise-regex.js.map