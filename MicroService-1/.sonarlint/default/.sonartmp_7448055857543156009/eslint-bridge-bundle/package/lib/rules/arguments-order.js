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
// https://jira.sonarsource.com/browse/RSPEC-2234
Object.defineProperty(exports, "__esModule", { value: true });
exports.rule = void 0;
const utils_1 = require("./utils");
const isRequiredParserServices_1 = require("../utils/isRequiredParserServices");
const nodes_1 = require("eslint-plugin-sonarjs/lib/utils/nodes");
exports.rule = {
    meta: {
        schema: [
            {
                // internal parameter for rules having secondary locations
                enum: ['sonar-runtime'],
            },
        ],
    },
    create(context) {
        const services = context.parserServices;
        const canResolveType = isRequiredParserServices_1.isRequiredParserServices(services);
        function checkArguments(functionCall) {
            const resolvedFunction = resolveFunctionDeclaration(functionCall);
            if (!resolvedFunction) {
                return;
            }
            const { params: functionParameters, declaration: functionDeclaration } = resolvedFunction;
            const argumentNames = functionCall.arguments.map(arg => nodes_1.isIdentifier(arg) ? arg.name : undefined);
            for (let argumentIndex = 0; argumentIndex < argumentNames.length; argumentIndex++) {
                const argumentName = argumentNames[argumentIndex];
                if (argumentName) {
                    const swappedArgumentName = getSwappedArgumentName(argumentNames, functionParameters, argumentName, argumentIndex, functionCall);
                    if (swappedArgumentName) {
                        raiseIssue(argumentName, swappedArgumentName, functionDeclaration, functionCall);
                        return;
                    }
                }
            }
        }
        function resolveFunctionDeclaration(node) {
            if (canResolveType) {
                return resolveFromTSSignature(node);
            }
            let functionDeclaration = null;
            if (utils_1.isFunctionNode(node.callee)) {
                functionDeclaration = node.callee;
            }
            else if (node.callee.type === 'Identifier') {
                functionDeclaration = resolveFromFunctionReference(node.callee);
            }
            if (!functionDeclaration) {
                return null;
            }
            return {
                params: extractFunctionParameters(functionDeclaration),
                declaration: functionDeclaration,
            };
        }
        function resolveFromTSSignature(node) {
            const signature = utils_1.getSignatureFromCallee(node, services);
            if (signature && signature.declaration) {
                return {
                    params: signature.parameters.map(param => param.name),
                    declaration: services.tsNodeToESTreeNodeMap.get(signature.declaration),
                };
            }
            return null;
        }
        function resolveFromFunctionReference(functionIdentifier) {
            const reference = context
                .getScope()
                .references.find(ref => ref.identifier === functionIdentifier);
            if (reference &&
                reference.resolved &&
                reference.resolved.defs.length === 1 &&
                reference.resolved.defs[0] &&
                reference.resolved.defs[0].type === 'FunctionName') {
                return reference.resolved.defs[0].node;
            }
            return null;
        }
        function getSwappedArgumentName(argumentNames, functionParameters, argumentName, argumentIndex, node) {
            const indexInFunctionDeclaration = functionParameters.findIndex(functionParameterName => functionParameterName === argumentName);
            if (indexInFunctionDeclaration >= 0 && indexInFunctionDeclaration !== argumentIndex) {
                const potentiallySwappedArgument = argumentNames[indexInFunctionDeclaration];
                if (potentiallySwappedArgument &&
                    potentiallySwappedArgument === functionParameters[argumentIndex] &&
                    haveCompatibleTypes(node.arguments[argumentIndex], node.arguments[indexInFunctionDeclaration])) {
                    return potentiallySwappedArgument;
                }
            }
            return null;
        }
        function haveCompatibleTypes(arg1, arg2) {
            if (canResolveType) {
                const type1 = normalizeType(utils_1.getTypeAsString(arg1, services));
                const type2 = normalizeType(utils_1.getTypeAsString(arg2, services));
                return type1 === type2;
            }
            return true;
        }
        function raiseIssue(arg1, arg2, functionDeclaration, node) {
            const primaryMessage = `Arguments '${arg1}' and '${arg2}' have the same names but not the same order as the function parameters.`;
            const encodedMessage = {
                message: primaryMessage,
                secondaryLocations: getSecondaryLocations(functionDeclaration),
            };
            context.report({
                message: JSON.stringify(encodedMessage),
                loc: getParametersClauseLocation(node.arguments),
            });
        }
        return {
            NewExpression: (node) => {
                checkArguments(node);
            },
            CallExpression: (node) => {
                checkArguments(node);
            },
        };
    },
};
function extractFunctionParameters(functionDeclaration) {
    return functionDeclaration.params.map(param => {
        const identifiers = utils_1.resolveIdentifiers(param);
        if (identifiers.length === 1 && identifiers[0]) {
            return identifiers[0].name;
        }
        return undefined;
    });
}
function getSecondaryLocations(functionDeclaration) {
    if (functionDeclaration && functionDeclaration.params && functionDeclaration.params.length > 0) {
        const { start, end } = getParametersClauseLocation(functionDeclaration.params);
        return [
            {
                message: 'Formal parameters',
                line: start.line,
                column: start.column,
                endLine: end.line,
                endColumn: end.column,
            },
        ];
    }
    return [];
}
function getParametersClauseLocation(parameters) {
    const firstParam = parameters[0];
    const lastParam = parameters[parameters.length - 1];
    return { start: firstParam.loc.start, end: lastParam.loc.end };
}
function normalizeType(typeAsString) {
    switch (typeAsString) {
        case 'String':
            return 'string';
        case 'Boolean':
            return 'boolean';
        case 'Number':
            return 'number';
        default:
            return typeAsString;
    }
}
//# sourceMappingURL=arguments-order.js.map