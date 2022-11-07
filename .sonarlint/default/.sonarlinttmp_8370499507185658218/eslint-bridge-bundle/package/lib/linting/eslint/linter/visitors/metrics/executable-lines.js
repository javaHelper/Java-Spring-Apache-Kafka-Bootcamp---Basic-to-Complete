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
exports.findExecutableLines = void 0;
const eslint_1 = require("linting/eslint");
/**
 * The ESLint executable node types
 */
const EXECUTABLE_NODES = [
    'ExpressionStatement',
    'IfStatement',
    'LabeledStatement',
    'BreakStatement',
    'ContinueStatement',
    'WithStatement',
    'SwitchStatement',
    'ReturnStatement',
    'ThrowStatement',
    'TryStatement',
    'WhileStatement',
    'DoWhileStatement',
    'ForStatement',
    'ForInStatement',
    'DebuggerStatement',
    'VariableDeclaration',
    'ForOfStatement',
];
/**
 * Finds the line numbers of executable lines in the source code
 */
function findExecutableLines(sourceCode) {
    const lines = new Set();
    (0, eslint_1.visit)(sourceCode, node => {
        if (EXECUTABLE_NODES.includes(node.type) && node.loc) {
            lines.add(node.loc.start.line);
        }
    });
    return Array.from(lines).sort((a, b) => a - b);
}
exports.findExecutableLines = findExecutableLines;
//# sourceMappingURL=executable-lines.js.map