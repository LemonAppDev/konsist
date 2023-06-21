package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.architecture.KoArchitectureImpl
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException

@Suppress("detekt.ThrowsCount")
fun KoScope.assert(architecture: KoArchitecture) {
    try {
        val files = files()

        val layerHasValidArchitecture = (architecture as KoArchitectureImpl)
            .dependencies
            .map { (t, u) ->
                val otherLayers = (architecture.allLayers - u).map { it.isDefinedBy }

                files
                    .withPackage(t.isDefinedBy)
                    .all { otherLayers.all { name -> !it.hasImports(name) } }
            }

        val result = mutableMapOf<Layer, Boolean>()

        architecture
            .dependencies
            .keys
            .forEachIndexed { index, layer -> result[layer] = layerHasValidArchitecture[index] }

        val failedLayers = mutableListOf<Layer>()

        result.forEach { (t, u) ->
            if (!u) {
                failedLayers += t
            }
        }

        val allChecksPassed = failedLayers.isEmpty()

        if (!allChecksPassed) {
            throw KoCheckFailedException(getCheckFailedMessages(failedLayers))
        }
    } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

private fun getCheckFailedMessages(failedDeclarations: List<Layer>): String {
    val failedDeclarationsMessage = failedDeclarations.joinToString("\n")

    return "Assert '${getTestMethodName()}' has failed. Invalid dependencies at (${failedDeclarations.size}):\n$failedDeclarationsMessage"
}

/**
 * In this call stack hierarchy test name is at index 5.
 */
private fun getTestMethodName(): String? {
    val stackTraceIndexOfTestMethod = 5
    return Thread.currentThread().stackTrace[stackTraceIndexOfTestMethod].methodName
}
