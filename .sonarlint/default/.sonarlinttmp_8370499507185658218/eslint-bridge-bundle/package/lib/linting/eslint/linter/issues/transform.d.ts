import { Linter, Rule, SourceCode } from 'eslint';
import { Issue } from './issue';
/**
 * Transforms ESLint messages into SonarQube issues
 *
 * Transforming an ESLint message into a SonarQube issue implies:
 * - converting ESLint messages into SonarQube issues
 * - converting ESLint fixes into SonarLint quick fixes
 * - decoding encoded secondary locations
 * - normalizing issue locations
 *
 * @param messages ESLint messages to transform
 * @param ctx contextual information
 * @returns the transformed issues
 */
export declare function transformMessages(messages: Linter.LintMessage[], ctx: {
    sourceCode: SourceCode;
    rules: Map<string, Rule.RuleModule>;
}): Issue[];
