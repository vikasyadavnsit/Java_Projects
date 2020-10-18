"use strict";
/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2020 SonarSource SA
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
// Greatly inspired by https://github.com/eslint/eslint/blob/561b6d4726f3e77dd40ba0d340ca7f08429cd2eb/lib/rules/max-lines-per-function.js
// We had to fork the implementation to control the reporting (issue location), in order to provide a better user experience.
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const locations_1 = require("eslint-plugin-sonarjs/lib/utils/locations");
const nodes_1 = require("eslint-plugin-sonarjs/lib/utils/nodes");
exports.rule = {
    meta: {
        schema: [{ type: 'integer' }],
    },
    create(context) {
        const [threshold] = context.options;
        const sourceCode = context.getSourceCode();
        const lines = sourceCode.lines;
        const commentLineNumbers = getCommentLineNumbers(sourceCode.getAllComments());
        return {
            'FunctionDeclaration, FunctionExpression, ArrowFunctionExpression': (node) => {
                const parent = nodes_1.getParent(context);
                if (!node.loc || isIIFE(node, parent)) {
                    return;
                }
                let lineCount = 0;
                for (let i = node.loc.start.line - 1; i < node.loc.end.line; ++i) {
                    const line = lines[i];
                    if (commentLineNumbers.has(i + 1) &&
                        isFullLineComment(line, i + 1, commentLineNumbers.get(i + 1))) {
                        continue;
                    }
                    if (line.match(/^\s*$/u)) {
                        continue;
                    }
                    lineCount++;
                }
                if (lineCount > threshold) {
                    context.report({
                        message: `This function has ${lineCount} lines, which is greater than the ${threshold} lines authorized. Split it into smaller functions.`,
                        loc: locations_1.getMainFunctionTokenLocation(node, nodes_1.getParent(context), context),
                    });
                }
            },
        };
    },
};
function getCommentLineNumbers(comments) {
    const map = new Map();
    comments.forEach(comment => {
        if (comment.loc) {
            for (let i = comment.loc.start.line; i <= comment.loc.end.line; i++) {
                map.set(i, comment);
            }
        }
    });
    return map;
}
function isFullLineComment(line, lineNumber, comment) {
    if (!comment.loc) {
        return false;
    }
    const start = comment.loc.start, end = comment.loc.end, isFirstTokenOnLine = start.line === lineNumber && !line.slice(0, start.column).trim(), isLastTokenOnLine = end.line === lineNumber && !line.slice(end.column).trim();
    return (comment &&
        (start.line < lineNumber || isFirstTokenOnLine) &&
        (end.line > lineNumber || isLastTokenOnLine));
}
function isIIFE(node, parent) {
    return (node.type === 'FunctionExpression' &&
        parent &&
        parent.type === 'CallExpression' &&
        parent.callee === node);
}
//# sourceMappingURL=sonar-max-lines-per-function.js.map