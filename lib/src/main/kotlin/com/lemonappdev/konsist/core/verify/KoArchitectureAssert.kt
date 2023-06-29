package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.architecture.DependencyRulesImpl
import com.lemonappdev.konsist.core.architecture.KoArchitectureScope
import com.lemonappdev.konsist.core.architecture.LayerImpl
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureScope.assert() {
    try {
        val files = this.koScope.files()
        val architecture = this.koArchitecture as DependencyRulesImpl

        val isAllLayersValid = architecture.allLayers
            .all {
                files
                    .withPackage(it.definedBy)
                    .toList()
                    .isNotEmpty()
            }

        if (!isAllLayersValid) {
            val layer = architecture
                .allLayers
                .first {
                    files
                        .withPackage(it.definedBy)
                        .toList()
                        .isEmpty()
                }
            throw KoPreconditionFailedException("Layer ${layer.name} doesn't contain any files.")
        }

        val layerHasValidArchitecture = architecture
            .dependencies
            .map { (t, u) ->
                val otherLayers = (architecture.allLayers - u).map { it.definedBy }

                files
                    .withPackage(t.definedBy)
                    .filter { otherLayers.any { name -> it.hasImports(name) } }
                    .map { it.path }
                    .joinToString("\n")
            }

        val result = mutableMapOf<LayerImpl, String>()

        architecture
            .dependencies
            .keys
            .forEachIndexed { index, layer -> result[layer] = layerHasValidArchitecture[index] }

        val failedLayers = mutableListOf<LayerImpl>()

        result.forEach { (t, u) ->
            if (u.toList().isNotEmpty()) {
                failedLayers += t
            }
        }

        val filtered = result.filter { it.value.toList().isNotEmpty() }

        val allChecksPassed = failedLayers.isEmpty()

        if (!allChecksPassed) {
            throw KoCheckFailedException(getCheckFailedMessages(filtered))
        }
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

private fun getCheckFailedMessages(failedDeclarations: Map<LayerImpl, String>): String {
    val failedDeclarationsMessage = failedDeclarations.keys.mapIndexed { index, layer ->
        "Layer: ${layer.name}. Invalid files:\n${failedDeclarations.values.toList()[index]}"
    }.joinToString("\n")

    /**
     * In this call stack hierarchy test name is at index 9.
     */
    val index = 9

    return "Assert '${getTestMethodName(index)}' has failed. Invalid dependencies (${failedDeclarations.size}):" +
        "\n$failedDeclarationsMessage"
}
