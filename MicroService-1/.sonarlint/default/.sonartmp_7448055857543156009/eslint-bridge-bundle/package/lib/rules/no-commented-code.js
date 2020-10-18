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
// https://jira.sonarsource.com/browse/RSPEC-125
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const parser_1 = require("../parser");
const EXCLUDED_STATEMENTS = ['BreakStatement', 'LabeledStatement', 'ContinueStatement'];
exports.rule = {
    create(context) {
        function getGroupedComments(comments) {
            const groupedComments = [];
            let currentGroup = [];
            for (const comment of comments) {
                if (comment.type === 'Block') {
                    groupedComments.push({ value: comment.value, nodes: [comment] });
                }
                else if (currentGroup.length === 0 ||
                    areAdjacentLineComments(currentGroup[currentGroup.length - 1], comment)) {
                    currentGroup.push(comment);
                }
                else {
                    groupedComments.push({
                        value: currentGroup.map(lineComment => lineComment.value).join('\n'),
                        nodes: currentGroup,
                    });
                    currentGroup = [comment];
                }
            }
            if (currentGroup.length > 0) {
                groupedComments.push({
                    value: currentGroup.map(lineComment => lineComment.value).join('\n'),
                    nodes: currentGroup,
                });
            }
            return groupedComments;
        }
        function areAdjacentLineComments(previous, next) {
            const nextCommentLine = next.loc.start.line;
            if (previous.loc.start.line + 1 === nextCommentLine) {
                const nextCodeToken = context.getSourceCode().getTokenAfter(previous);
                return !nextCodeToken || nextCodeToken.loc.start.line > nextCommentLine;
            }
            return false;
        }
        return {
            'Program:exit': () => {
                const groupedComments = getGroupedComments(context.getSourceCode().getAllComments());
                groupedComments.forEach(groupComment => {
                    const rawTextTrimmed = groupComment.value.trim();
                    if (rawTextTrimmed !== '}' && containsCode(injectMissingBraces(rawTextTrimmed))) {
                        context.report({
                            message: 'Remove this commented out code.',
                            loc: getCommentLocation(groupComment.nodes),
                        });
                    }
                });
            },
        };
    },
};
function isExpressionExclusion(statement, code) {
    if (statement.type === 'ExpressionStatement') {
        const expression = statement.expression;
        if (expression.type === 'Identifier' ||
            expression.type === 'SequenceExpression' ||
            isUnaryPlusOrMinus(expression) ||
            isExcludedLiteral(expression) ||
            !code.getLastToken(statement, token => token.value === ';')) {
            return true;
        }
    }
    return false;
}
function isExclusion(parsedBody, code) {
    if (parsedBody.length === 1) {
        const singleStatement = parsedBody[0];
        return (EXCLUDED_STATEMENTS.includes(singleStatement.type) ||
            isReturnThrowExclusion(singleStatement) ||
            isExpressionExclusion(singleStatement, code));
    }
    return false;
}
function containsCode(value) {
    const parseResult = parser_1.parseJavaScriptSourceFile(value);
    return (isSourceCode(parseResult) &&
        parseResult.ast.body.length > 0 &&
        !isExclusion(parseResult.ast.body, parseResult));
}
function injectMissingBraces(value) {
    const openCurlyBraceNum = (value.match(/{/g) || []).length;
    const closeCurlyBraceNum = (value.match(/}/g) || []).length;
    const missingBraces = openCurlyBraceNum - closeCurlyBraceNum;
    if (missingBraces > 0) {
        return value + Array(missingBraces).fill('}').join('');
    }
    else if (missingBraces < 0) {
        return Array(-missingBraces).fill('{').join('') + value;
    }
    else {
        return value;
    }
}
function getCommentLocation(nodes) {
    return {
        start: nodes[0].loc.start,
        end: nodes[nodes.length - 1].loc.end,
    };
}
function isSourceCode(parseResult) {
    return !!parseResult.ast;
}
function isReturnThrowExclusion(statement) {
    if (statement.type === 'ReturnStatement' || statement.type === 'ThrowStatement') {
        return statement.argument == null || statement.argument.type === 'Identifier';
    }
    return false;
}
function isUnaryPlusOrMinus(expression) {
    return (expression.type === 'UnaryExpression' &&
        (expression.operator === '+' || expression.operator === '-'));
}
function isExcludedLiteral(expression) {
    if (expression.type === 'Literal') {
        return typeof expression.value === 'string' || typeof expression.value === 'number';
    }
    return false;
}
//# sourceMappingURL=no-commented-code.js.map