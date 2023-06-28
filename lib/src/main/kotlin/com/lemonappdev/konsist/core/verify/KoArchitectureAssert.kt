package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.architecture.KoArchitectureImpl
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

@Suppress("detekt.ThrowsCount")
fun KoArchitecture.assert(koScope: KoScope) {
    try {
        val files = koScope.files()

        val isAllLayersValid = (this as KoArchitectureImpl).allLayers
            .all {
                files
                    .withPackage(it.definedBy)
                    .toList()
                    .isNotEmpty()
            }

        if (!isAllLayersValid) {
            val layer = this
                .allLayers
                .first {
                    files
                        .withPackage(it.definedBy)
                        .toList()
                        .isEmpty()
                }
            throw KoPreconditionFailedException("Layer ${layer.name} doesn't contain any files.")
        }

        val layerHasValidArchitecture = (this as KoArchitectureImpl)
            .dependencies
            .map { (t, u) ->
                val otherLayers = (this.allLayers - u).map { it.definedBy }

                files
                    .withPackage(t.definedBy)
                    .filter { otherLayers.any { name -> it.hasImports(name) } }
                    .map { it.path }
                    .joinToString("\n")
            }

        val result = mutableMapOf<Layer, String>()

        this
            .dependencies
            .keys
            .forEachIndexed { index, layer -> result[layer] = layerHasValidArchitecture[index] }

        val failedLayers = mutableListOf<Layer>()

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

private fun getCheckFailedMessages(failedDeclarations: Map<Layer, String>): String {
    val failedDeclarationsMessage = failedDeclarations.keys.mapIndexed { index, layer ->
        "Layer: ${layer.name}. Invalid files:\n${failedDeclarations.values.toList()[index]}"
    }.joinToString("\n")

    /**
     * In this call stack hierarchy test name is at index 7.
     */
    val index = 7

    return "Assert '${getTestMethodName(index)}' has failed. Invalid dependencies (${failedDeclarations.size}):" +
            "\n$failedDeclarationsMessage"
}
