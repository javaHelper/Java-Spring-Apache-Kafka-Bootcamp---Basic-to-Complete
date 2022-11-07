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
// https://sonarsource.github.io/rspec/#/rspec/S5850/javascript
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const regex_1 = require("./helpers/regex");
var Position;
(function (Position) {
    Position[Position["BEGINNING"] = 0] = "BEGINNING";
    Position[Position["END"] = 1] = "END";
})(Position || (Position = {}));
exports.rule = (0, regex_1.createRegExpRule)(context => {
    return {
        onPatternEnter: (pattern) => {
            const { alternatives } = pattern;
            if (alternatives.length > 1 &&
                (anchoredAt(alternatives, Position.BEGINNING) || anchoredAt(alternatives, Position.END)) &&
                notAnchoredElseWhere(alternatives)) {
                context.reportRegExpNode({
                    message: 'Group parts of the regex together to make the intended operator precedence explicit.',
                    node: context.node,
                    regexpNode: pattern,
                });
            }
        },
    };
});
function anchoredAt(alternatives, position) {
    const itemIndex = position === Position.BEGINNING ? 0 : alternatives.length - 1;
    const firstOrLast = alternatives[itemIndex];
    return isAnchored(firstOrLast, position);
}
function notAnchoredElseWhere(alternatives) {
    if (isAnchored(alternatives[0], Position.END) ||
        isAnchored(alternatives[alternatives.length - 1], Position.BEGINNING)) {
        return false;
    }
    for (const alternative of alternatives.slice(1, alternatives.length - 1)) {
        if (isAnchored(alternative, Position.BEGINNING) || isAnchored(alternative, Position.END)) {
            return false;
        }
    }
    return true;
}
function isAnchored(alternative, position) {
    const { elements } = alternative;
    if (elements.length === 0) {
        return false;
    }
    const index = position === Position.BEGINNING ? 0 : elements.length - 1;
    const firstOrLast = elements[index];
    return isAnchor(firstOrLast);
}
function isAnchor(element) {
    return element.type === 'Assertion' && (element.kind === 'start' || element.kind === 'end');
}
//# sourceMappingURL=anchor-precedence.js.map