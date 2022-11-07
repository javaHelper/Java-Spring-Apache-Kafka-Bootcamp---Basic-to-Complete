import { Rule } from 'eslint';
/**
 * Extracts the schema of a rule
 * @param ruleModule the rule definition
 * @param ruleId the rule id
 * @returns the extracted rule schema, if any
 */
export declare function getRuleSchema(ruleModule: Rule.RuleModule | undefined, ruleId: string): import("json-schema").JSONSchema4[] | undefined;
