"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.decorateChaiFriendly = exports.decorateTypescriptEslint = void 0;
const decorators_1 = require("../utils/decorators");
function decorateTypescriptEslint(rule) {
    return decorators_1.interceptReport(rule, reportExempting(expr => isNegatedIife(expr) || containsChaiExpect(expr) || containsValidChaiShould(expr)));
}
exports.decorateTypescriptEslint = decorateTypescriptEslint;
function decorateChaiFriendly(rule) {
    return decorators_1.interceptReport(rule, reportExempting(isNegatedIife));
}
exports.decorateChaiFriendly = decorateChaiFriendly;
function reportExempting(exemptionCondition) {
    return (context, reportDescriptor) => {
        if ('node' in reportDescriptor) {
            const n = reportDescriptor['node'];
            const expr = n.expression;
            if (!exemptionCondition(expr)) {
                context.report(reportDescriptor);
            }
        }
    };
}
function containsChaiExpect(node) {
    if (node.type === 'CallExpression') {
        if (node.callee.type === 'Identifier' && node.callee.name === 'expect') {
            return true;
        }
        else {
            return containsChaiExpect(node.callee);
        }
    }
    else if (node.type === 'MemberExpression') {
        return containsChaiExpect(node.object);
    }
    return false;
}
function containsValidChaiShould(node, isSubexpr = false) {
    if (node.type === 'CallExpression') {
        return containsValidChaiShould(node.callee, true);
    }
    else if (node.type === 'MemberExpression') {
        if (node.property && node.property.type === 'Identifier' && node.property.name === 'should') {
            // Expressions like `x.should` are valid only as subexpressions, not on top level
            return isSubexpr;
        }
        else {
            return containsValidChaiShould(node.object, true);
        }
    }
    return false;
}
function isNegatedIife(node) {
    return node.type === 'UnaryExpression' && node.operator === '!' && isIife(node.argument);
}
function isIife(node) {
    return (node.type === 'CallExpression' &&
        (node.callee.type === 'FunctionExpression' || node.callee.type === 'ArrowFunctionExpression'));
}
//# sourceMappingURL=no-unused-expressions-decorator.js.map