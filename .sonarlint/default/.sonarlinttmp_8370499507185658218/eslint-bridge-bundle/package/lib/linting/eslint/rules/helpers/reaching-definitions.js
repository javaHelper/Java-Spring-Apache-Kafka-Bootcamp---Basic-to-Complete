"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getVariableFromIdentifier = exports.resolveAssignedValues = exports.ReachingDefinitions = exports.reachingDefinitions = exports.unknownValue = void 0;
class AssignedValues extends Set {
    constructor() {
        super(...arguments);
        this.type = 'AssignedValues';
    }
}
const assignedValues = (val) => new AssignedValues([val]);
exports.unknownValue = {
    type: 'UnknownValue',
};
function reachingDefinitions(reachingDefinitionsMap) {
    const worklist = Array.from(reachingDefinitionsMap.values(), defs => defs.segment);
    while (worklist.length > 0) {
        const current = worklist.pop();
        const reachingDefs = reachingDefinitionsMap.get(current.id);
        const outHasChanged = reachingDefs.propagate(reachingDefinitionsMap);
        if (outHasChanged) {
            current.nextSegments.forEach(next => worklist.push(next));
        }
    }
}
exports.reachingDefinitions = reachingDefinitions;
class ReachingDefinitions {
    constructor(segment) {
        this.in = new Map();
        this.out = new Map();
        /**
         * collects references in order they are evaluated, set in JS maintains insertion order
         */
        this.references = new Set();
        this.segment = segment;
    }
    add(ref) {
        const variable = ref.resolved;
        if (variable) {
            this.references.add(ref);
        }
    }
    propagate(reachingDefinitionsMap) {
        this.in.clear();
        this.segment.prevSegments.forEach(prev => {
            this.join(reachingDefinitionsMap.get(prev.id).out);
        });
        const newOut = new Map(this.in);
        this.references.forEach(ref => this.updateProgramState(ref, newOut));
        if (!equals(this.out, newOut)) {
            this.out = newOut;
            return true;
        }
        else {
            return false;
        }
    }
    updateProgramState(ref, programState) {
        const variable = ref.resolved;
        if (!variable || !ref.isWrite()) {
            return;
        }
        if (!ref.writeExpr) {
            programState.set(variable, exports.unknownValue);
            return;
        }
        const rhsValues = resolveAssignedValues(variable, ref.writeExpr, programState, ref.from);
        programState.set(variable, rhsValues);
    }
    join(previousOut) {
        for (const [key, values] of previousOut.entries()) {
            const inValues = this.in.get(key) || new AssignedValues();
            if (inValues.type === 'AssignedValues' && values.type === 'AssignedValues') {
                values.forEach(val => inValues.add(val));
                this.in.set(key, inValues);
            }
            else {
                this.in.set(key, exports.unknownValue);
            }
        }
    }
}
exports.ReachingDefinitions = ReachingDefinitions;
function resolveAssignedValues(lhsVariable, writeExpr, assignedValuesMap, scope) {
    if (!writeExpr) {
        return exports.unknownValue;
    }
    switch (writeExpr.type) {
        case 'Literal':
            return writeExpr.raw ? assignedValues(writeExpr.raw) : exports.unknownValue;
        case 'Identifier':
            const resolvedVar = getVariableFromIdentifier(writeExpr, scope);
            if (resolvedVar && resolvedVar !== lhsVariable) {
                const resolvedAssignedValues = assignedValuesMap.get(resolvedVar);
                return resolvedAssignedValues || exports.unknownValue;
            }
            return exports.unknownValue;
        default:
            return exports.unknownValue;
    }
}
exports.resolveAssignedValues = resolveAssignedValues;
function equals(ps1, ps2) {
    if (ps1.size !== ps2.size) {
        return false;
    }
    for (const [variable, values1] of ps1) {
        const values2 = ps2.get(variable);
        if (!values2 || !valuesEquals(values2, values1)) {
            return false;
        }
    }
    return true;
}
function valuesEquals(a, b) {
    if (a.type === 'AssignedValues' && b.type === 'AssignedValues') {
        return setEquals(a, b);
    }
    return a === b;
}
function setEquals(a, b) {
    return a.size === b.size && [...a].every(e => b.has(e));
}
function getVariableFromIdentifier(identifier, scope) {
    let variable = scope.variables.find(value => value.name === identifier.name);
    if (!variable && scope.upper) {
        variable = scope.upper.variables.find(value => value.name === identifier.name);
    }
    return variable;
}
exports.getVariableFromIdentifier = getVariableFromIdentifier;
//# sourceMappingURL=reaching-definitions.js.map