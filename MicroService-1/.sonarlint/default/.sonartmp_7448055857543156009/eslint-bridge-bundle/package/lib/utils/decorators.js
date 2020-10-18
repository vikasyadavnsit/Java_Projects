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
Object.defineProperty(exports, "__esModule", { value: true });
exports.interceptReport = void 0;
const NUM_ARGS_NODE_MESSAGE = 2;
/**
 * Modifies the behavior of `context.report(descriptor)` for a given rule.
 *
 * Useful for performing additional checks before reporting an issue.
 *
 * @param rule the original rule
 * @param onReport replacement for `context.report(descr)`
 *                 invocations used inside of the rule
 */
function interceptReport(rule, onReport) {
    return {
        meta: rule.meta,
        create(originalContext) {
            const interceptingContext = {
                id: originalContext.id,
                options: originalContext.options,
                settings: originalContext.settings,
                parserPath: originalContext.parserPath,
                parserOptions: originalContext.parserOptions,
                parserServices: originalContext.parserServices,
                getAncestors() {
                    return originalContext.getAncestors();
                },
                getDeclaredVariables(node) {
                    return originalContext.getDeclaredVariables(node);
                },
                getFilename() {
                    return originalContext.getFilename();
                },
                getScope() {
                    return originalContext.getScope();
                },
                getSourceCode() {
                    return originalContext.getSourceCode();
                },
                markVariableAsUsed(name) {
                    return originalContext.markVariableAsUsed(name);
                },
                report(...args) {
                    let descr = undefined;
                    if (args.length === 1) {
                        descr = args[0];
                    }
                    else if (args.length === NUM_ARGS_NODE_MESSAGE && typeof args[1] === 'string') {
                        // not declared in the `.d.ts`, but used in practice by rules written in JS
                        descr = {
                            node: args[0],
                            message: args[1],
                        };
                    }
                    if (descr) {
                        onReport(originalContext, descr);
                    }
                },
            };
            return rule.create(interceptingContext);
        },
    };
}
exports.interceptReport = interceptReport;
//# sourceMappingURL=decorators.js.map