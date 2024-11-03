package com.lemonappdev.konsist.core.architecture.validator

import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.validator.rule.CircularDependencyDependenciesRule
import com.lemonappdev.konsist.core.architecture.validator.rule.DependOnLayerThenDependentOnNothingRule
import com.lemonappdev.konsist.core.architecture.validator.rule.DependentOnNothingThenDependOnLayerDependenciesRule
import com.lemonappdev.konsist.core.architecture.validator.rule.LayerDependenciesRule
import com.lemonappdev.konsist.core.architecture.validator.rule.UniqueLayerRule

internal class LayerValidatorManager(
    private val rules: List<LayerDependenciesRule> = defaultRules,
) {
    fun validateLayerDependencies(dependencies: Set<LayerDependency>) {
        rules.forEach { rule ->
            rule.validate(dependencies)
        }
    }

    companion object {
        private val defaultRules =
            listOf(
                UniqueLayerRule(),
                DependentOnNothingThenDependOnLayerDependenciesRule(),
                DependOnLayerThenDependentOnNothingRule(),
                CircularDependencyDependenciesRule(),
            )
    }
}
