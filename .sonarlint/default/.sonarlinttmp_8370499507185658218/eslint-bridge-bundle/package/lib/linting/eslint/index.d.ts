import { LinterWrapper, RuleConfig } from './linter';
export * from './linter';
export * from './rules';
/**
 * The global ESLint linter wrapper
 *
 * The global linter wrapper is expected to be initialized before use.
 * To this end, the plugin is expected to explicitey send a request to
 * initialize the linter before starting the actual analysis of a file.
 */
export declare let linter: LinterWrapper;
/**
 * Initializes the global linter wrapper
 * @param inputRules the rules from the active quality profiles
 * @param environments the JavaScript execution environments
 * @param globals the global variables
 */
export declare function initializeLinter(inputRules: RuleConfig[], environments?: string[], globals?: string[]): void;
export declare class LinterError extends Error {
}
/**
 * Throws a runtime error if the global linter wrapper is not initialized.
 */
export declare function assertLinterInitialized(): void;
