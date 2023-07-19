package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.architecture.DependencyRulesImpl
import com.lemonappdev.konsist.core.architecture.KoArchitectureScope
import com.lemonappdev.konsist.core.architecture.Status
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureScope.assert() {
    try {
        val files = this.koScope.files
        val dependencyRules = this.dependencyRules as DependencyRulesImpl

        if (dependencyRules.allLayers.isEmpty()) {
            throw KoPreconditionFailedException("Architecture doesn't contain layers or dependencies.")
        }

        val isAllLayersValid = dependencyRules.allLayers
            .all {
                files
                    .withPackage(it.definedBy)
                    .toList()
                    .isNotEmpty()
            }

        if (!isAllLayersValid) {
            val layer = dependencyRules
                .allLayers
                .first {
                    files
                        .withPackage(it.definedBy)
                        .toList()
                        .isEmpty()
                }
            throw KoPreconditionFailedException("Layer ${layer.name} doesn't contain any files.")
        }

        val layerHasValidArchitecture = dependencyRules
            .dependencies
            .map { (t, u) ->
                val otherLayers = (dependencyRules.allLayers - u).map { it.definedBy }

                files
                    .withPackage(t.definedBy)
                    .filter { otherLayers.any { name -> it.hasImports(name) } }
                    .map { it.path }
                    .joinToString("\n")
            }

        val result = mutableMapOf<Layer, String>()

        dependencyRules
            .dependencies
            .keys
            .forEachIndexed { index, layer -> result[layer] = layerHasValidArchitecture[index] }

        val failedLayers = mutableListOf<Layer>()

        result.forEach { (t, u) ->
            if (u.toList().isNotEmpty()) {
                failedLayers += t
            }
        }

        val filtered = result
            .filter { it.value.toList().isNotEmpty() }

        val allChecksPassed = failedLayers.isEmpty()

        if (!allChecksPassed) {
            throw KoCheckFailedException(getCheckFailedMessages(filtered, dependencyRules.dependencies, dependencyRules.statuses))
        }
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

private fun getCheckFailedMessages(
    failedDeclarations: Map<Layer, String>,
    dependencies: Map<Layer, Set<Layer>>,
    statuses: Map<Layer, Status>,
): String {
    val failedDeclarationsMessage = failedDeclarations
        .keys
        .mapIndexed { index, layer ->
            val layerDependencies = (dependencies.getOrDefault(layer, emptySet()) - layer).map { it.name }

            val message = when (statuses.getOrDefault(layer, Status.NONE)) {
                Status.DEPEND_ON_LAYER -> {
                    "depends on ${layerDependencies.joinToString(", ")} assertion failure:"
                }

                Status.DEPENDENT_ON_NOTHING -> {
                    "depends on nothing assertion failure:"
                }

                else -> {
                    ""
                }
            }

            "${layer.name} $message\n${failedDeclarations.values.toList()[index]}"
        }
        .joinToString("\n")

    return "'${getTestMethodNameFromSeventhIndex()}' test has failed." +
        "\n$failedDeclarationsMessage"
}
