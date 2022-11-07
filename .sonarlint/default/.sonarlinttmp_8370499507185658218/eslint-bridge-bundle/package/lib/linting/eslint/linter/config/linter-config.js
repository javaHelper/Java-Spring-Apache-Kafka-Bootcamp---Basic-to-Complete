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
exports.createLinterConfig = void 0;
const helpers_1 = require("helpers");
const custom_rules_1 = require("../custom-rules");
const rule_config_1 = require("./rule-config");
/**
 * Creates an ESLint linting configuration
 *
 * A linter configuration is created based on the input rules enabled by
 * the user through the active quality profile and the rules provided by
 * the linter wrapper.
 *
 * The configuration includes the rules with their configuration that are
 * used during linting as well as the global variables and the JavaScript
 * execution environments defined through the analyzer's properties.
 *
 * @param inputRules the rules from the active quality profile
 * @param linterRules the wrapper's rule database
 * @param environments the JavaScript execution environments
 * @param globs the global variables
 * @returns the created ESLint linting configuration
 */
function createLinterConfig(inputRules, linterRules, environments, globs) {
    const env = createEnv(environments);
    const globals = createGlobals(globs);
    const parserOptions = { sourceType: 'module', ecmaVersion: 2018 };
    const config = {
        env,
        globals,
        parserOptions,
        rules: {},
        /* using "max" version to prevent `eslint-plugin-react` from printing a warning */
        settings: { react: { version: '999.999.999' } },
    };
    enableRules(config, inputRules, linterRules);
    enableInternalCustomRules(config);
    return config;
}
exports.createLinterConfig = createLinterConfig;
/**
 * Creates an ESLint execution environments configuration
 * @param environments the JavaScript execution environments to enable
 * @returns a configuration of JavaScript execution environments
 */
function createEnv(environments) {
    const env = { es6: true };
    for (const key of environments) {
        env[key] = true;
    }
    return env;
}
/**
 * Creates an ESLint global variables configuration
 * @param globs the global variables to enable
 * @returns a configuration of global variables
 */
function createGlobals(globs) {
    const globals = {};
    for (const key of globs) {
        globals[key] = true;
    }
    return globals;
}
/**
 * Enables input rules
 *
 * Enabling an input rule is similar to how rule enabling works with ESLint.
 * However, in the particular case of internal rules, the rule configuration
 * can be decorated with special markers to activate internal features.
 *
 * For example, an ESLint rule configuration for a rule that reports secondary
 * locations would be `["error", "sonar-runtime"]`, where the "sonar-runtime"`
 * is a marker for a post-linting processing to decode such locations.
 *
 * @param config the configuration to augment with rule enabling
 * @param inputRules the input rules to enable
 * @param linterRules the linter rules available
 */
function enableRules(config, inputRules, linterRules) {
    for (const inputRule of inputRules) {
        const ruleModule = linterRules.get(inputRule.key);
        config.rules[inputRule.key] = ['error', ...(0, rule_config_1.extendRuleConfig)(ruleModule, inputRule)];
    }
}
/**
 * Enables internal custom rules in the provided configuration
 *
 * Custom rules like cognitive complexity and symbol highlighting
 * are always enabled as part of metrics computation. Such rules
 * are, therefore, added in the linting configuration by default.
 *
 * _Internal custom rules are not enabled in SonarLint context._
 *
 * @param config the configuration to augment with custom rule enabling
 */
function enableInternalCustomRules(config) {
    if (!(0, helpers_1.getContext)().sonarlint) {
        for (const internalCustomRule of custom_rules_1.customRules) {
            config.rules[internalCustomRule.ruleId] = ['error', ...internalCustomRule.ruleConfig];
        }
    }
}
//# sourceMappingURL=linter-config.js.map