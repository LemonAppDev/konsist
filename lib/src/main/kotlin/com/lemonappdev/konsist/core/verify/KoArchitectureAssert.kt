package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.architecture.KoArchitectureFiles
import com.lemonappdev.konsist.core.architecture.KoArchitectureScope
import com.lemonappdev.konsist.core.architecture.LayerDependenciesCore
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureFiles.assert(
    additionalMessage: String?,
    testName: String?,
): Unit {
    try {
        val layerDependencies = this.layerDependencies as LayerDependenciesCore

        validateLayersDependencies(layerDependencies = layerDependencies)
        validateAllLayersAreValid(files = this.files, layerDependencies = layerDependencies)

        val failedFiles = validateLayersContainingFailedFiles(this.files, layerDependencies)

        if (failedFiles.isNotEmpty()) {
            throw KoAssertionFailedException(
//                getCheckFailedMessages(
//                    failedFiles.distinct(),
//                    layerDependencies.positiveDependencies,
//                    layerDependencies.negativeDependencies,
//                    layerDependencies.layerDependencyTypes,
//                    additionalMessage,
//                    testName,
//                ),
            )
        }
    } catch (e: KoException) {
        throw e
    } catch (
        @Suppress("detekt.TooGenericExceptionCaught") e: Exception,
    ) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureScope.assert(
    additionalMessage: String?,
    testName: String?,
): Unit {
    try {
        val files = this.koScope.files
        val layerDependencies = this.layerDependencies as LayerDependenciesCore

        validateLayersDependencies(layerDependencies = layerDependencies)
        validateAllLayersAreValid(files = files, layerDependencies = layerDependencies)

        val failedFiles = validateLayersContainingFailedFiles(files = files, layerDependencies = layerDependencies)

        if (failedFiles.isNotEmpty()) {
            throw KoAssertionFailedException(
//                getCheckFailedMessages(
//                    failedFiles.distinct(),
//                    layerDependencies.positiveDependencies,
//                    layerDependencies.negativeDependencies,
//                    layerDependencies.layerDependencyTypes,
//                    additionalMessage,
//                    testName,
//                ),
            )
        }
    } catch (e: KoException) {
        throw e
    } catch (
        @Suppress("detekt.TooGenericExceptionCaught") e: Exception,
    ) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

/**
 * Validate that all the layers are not empty
 *
 * @param files within the scope
 * @param layerDependencies dependencies of the architecture.
 *
 * @throws KoPreconditionFailedException Layers do not contain files
 */
private fun validateAllLayersAreValid(
    files: List<KoFileDeclaration>,
    layerDependencies: LayerDependenciesCore,
): Unit {
    val isAllLayersValid =
        layerDependencies.allLayers
            .all {
                files
                    .withPackage(it.rootPackage)
                    .isNotEmpty()
            }

    if (!isAllLayersValid) {
        val layer =
            layerDependencies
                .allLayers
                .first {
                    files
                        .withPackage(it.rootPackage)
                        .isEmpty()
                }
        throw KoPreconditionFailedException("Layer ${layer.name} doesn't contain any files.")
    }
}

/**
 * Validate dependencies among the Layers
 *
 * @param layerDependencies dependencies of the architecture.
 *
 * @throws KoPreconditionFailedException Architecture doesn't contain layers or dependencies
 */
private fun validateLayersDependencies(layerDependencies: LayerDependenciesCore): Unit {
    if (layerDependencies.allLayers.isEmpty()) {
        throw KoPreconditionFailedException("Architecture doesn't contain layers or dependencies.")
    }
}

/**
 * Validate layers do not contain [FailedFiles]
 *
 * @param files within the scope
 * @param layerDependencies dependencies of the architecture.
 *
 * @return List<FailedFiles>
 */
private fun validateLayersContainingFailedFiles(
    files: List<KoFileDeclaration>,
    layerDependencies: LayerDependenciesCore,
): List<FailedFiles> {
    val failedFiles = mutableListOf<FailedFiles>()

//    layerDependencies
//        .positiveDependencies
//        .forEach { (layer, layers) ->
//            val otherLayers = (layerDependencies.allLayers - layers)
//
//            files
//                .withPackage(layer.rootPackage)
//                .onEach {
//                    otherLayers.forEach { otherLayer ->
//                        val imports =
//                            it.imports.filter { import ->
//                                LocationUtil.resideInLocation(otherLayer.rootPackage, import.name)
//                            }
//
//                        if (imports.isNotEmpty()) {
//                            failedFiles += FailedFiles(it.path, layer, otherLayer.name, imports)
//                        }
//                    }
//                }
//        }
//
//    layerDependencies
//        .negativeDependencies
//        .forEach { (layer, layers) ->
//            files
//                .withPackage(layer.rootPackage)
//                .onEach {
//                    layers.forEach { otherLayer ->
//                        val imports =
//                            it.imports.filter { import ->
//                                LocationUtil.resideInLocation(otherLayer.rootPackage, import.name)
//                            }
//
//                        if (imports.isNotEmpty()) {
//                            failedFiles += FailedFiles(it.path, layer, otherLayer.name, imports)
//                        }
//                    }
//                }
//        }
    return failedFiles
}

private data class FailedFiles(
    val path: String,
    val resideInLayer: Layer,
    val failedLayer: String,
    val imports: List<KoImportDeclaration>,
)

@Suppress("detekt.LongParameterList")
private fun getCheckFailedMessages(
    failedFiles: List<FailedFiles>,
    positiveDependencies: Map<Layer, Set<Layer>>,
    negativeDependencies: Map<Layer, Set<Layer>>,
    statuses: Map<Layer, LayerDependencyType>,
    additionalMessage: String?,
    testName: String?,
): String {
    val failedDeclarationsMessage =
        failedFiles
            .map { it.resideInLayer }
            .joinToString("\n") { layer ->
                val details =
                    failedFiles
                        .filter { it.resideInLayer == layer }
                        .joinToString(separator = "\n") {
                            "A file ${it.path} in a ${it.resideInLayer.name} layer depends on ${it.failedLayer} layer, imports:\n${
                                it.imports.joinToString(
                                    separator = "\n",
                                ) { import -> "\t${import.name} (${import.location})" }
                            }"
                        }

                val positiveLayerDependencies = (positiveDependencies.getOrDefault(layer, emptySet()) - layer).map { it.name }

                val negativeLayerDependencies = (negativeDependencies.getOrDefault(layer, emptySet()) - layer).map { it.name }

                val message =
                    when (statuses.getOrDefault(layer, LayerDependencyType.NONE)) {
                        LayerDependencyType.DEPEND_ON_LAYER -> {
                            "depends on ${positiveLayerDependencies.joinToString(", ")} assertion failure:"
                        }

                        LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER -> {
                            "does not depend on ${negativeLayerDependencies.joinToString(", ")} assertion failure:"
                        }

                        LayerDependencyType.DEPENDENTS_ON_NOTHING -> {
                            "depends on nothing assertion failure:"
                        }

                        else -> {
                            ""
                        }
                    }

                "${layer.name} $message\n$details"
            }
    val customMessage = if (additionalMessage != null) "\n$additionalMessage" else ""

    val name = testName ?: getTestMethodNameFromEightIndex()

    return "'$name' test has failed.${customMessage}\n$failedDeclarationsMessage"
}
