package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.architecture.DependencyRulesCore
import com.lemonappdev.konsist.core.architecture.KoArchitectureFiles
import com.lemonappdev.konsist.core.architecture.KoArchitectureScope
import com.lemonappdev.konsist.core.architecture.Status
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureFiles.assert(
    additionalMessage: String?,
    testName: String?,
): Unit {
    try {
        val dependencyRules = this.dependencyRules as DependencyRulesCore

        validateLayersOnDependencyRules(dependencyRules = dependencyRules)
        validateAllLayersAreValid(files = this.files, dependencyRules = dependencyRules)

        val failedFiles = validateLayersContainingFailedFiles(this.files, dependencyRules)

        if (failedFiles.isNotEmpty()) {
            throw KoAssertionFailedException(
                getCheckFailedMessages(
                    failedFiles.distinct(),
                    dependencyRules.dependencies,
                    dependencyRules.statuses,
                    additionalMessage,
                    testName,
                ),
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
        val dependencyRules = this.dependencyRules as DependencyRulesCore

        validateLayersOnDependencyRules(dependencyRules = dependencyRules)
        validateAllLayersAreValid(files = files, dependencyRules = dependencyRules)

        val failedFiles = validateLayersContainingFailedFiles(files = files, dependencyRules = dependencyRules)

        if (failedFiles.isNotEmpty()) {
            throw KoAssertionFailedException(
                getCheckFailedMessages(
                    failedFiles.distinct(),
                    dependencyRules.dependencies,
                    dependencyRules.statuses,
                    additionalMessage,
                    testName,
                ),
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
 * @param dependencyRules dependencies of the architecture.
 *
 * @throws KoPreconditionFailedException Layers do not contain files
 */
private fun validateAllLayersAreValid(
    files: List<KoFileDeclaration>,
    dependencyRules: DependencyRulesCore,
): Unit {
    val isAllLayersValid =
        dependencyRules.allLayers
            .all {
                files
                    .withPackage(it.definedBy)
                    .isNotEmpty()
            }

    if (!isAllLayersValid) {
        val layer =
            dependencyRules
                .allLayers
                .first {
                    files
                        .withPackage(it.definedBy)
                        .isEmpty()
                }
        throw KoPreconditionFailedException("Layer ${layer.name} doesn't contain any files.")
    }
}

/**
 * Validate dependencies among the Layers
 *
 * @param dependencyRules dependencies of the architecture.
 *
 * @throws KoPreconditionFailedException Architecture doesn't contain layers or dependencies
 */
private fun validateLayersOnDependencyRules(dependencyRules: DependencyRulesCore): Unit {
    if (dependencyRules.allLayers.isEmpty()) {
        throw KoPreconditionFailedException("Architecture doesn't contain layers or dependencies.")
    }
}

/**
 * Validate layers do not contain [FailedFiles]
 *
 * @param files within the scope
 * @param dependencyRules dependencies of the architecture.
 *
 * @return List<FailedFiles>
 */
private fun validateLayersContainingFailedFiles(
    files: List<KoFileDeclaration>,
    dependencyRules: DependencyRulesCore,
): List<FailedFiles> {
    val failedFiles = mutableListOf<FailedFiles>()

    dependencyRules
        .dependencies
        .forEach { (layer, layers) ->
            val otherLayers = (dependencyRules.allLayers - layers)

            files
                .withPackage(layer.definedBy)
                .onEach {
                    otherLayers.forEach { otherLayer ->
                        val imports =
                            it.imports.filter { import ->
                                LocationUtil.resideInLocation(otherLayer.definedBy, import.name)
                            }

                        if (imports.isNotEmpty()) {
                            failedFiles += FailedFiles(it.path, layer, otherLayer.name, imports)
                        }
                    }
                }
        }
    return failedFiles
}

private data class FailedFiles(
    val path: String,
    val resideInLayer: Layer,
    val failedLayer: String,
    val imports: List<KoImportDeclaration>,
)

private fun getCheckFailedMessages(
    failedFiles: List<FailedFiles>,
    dependencies: Map<Layer, Set<Layer>>,
    statuses: Map<Layer, Status>,
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

                val layerDependencies = (dependencies.getOrDefault(layer, emptySet()) - layer).map { it.name }

                val message =
                    when (statuses.getOrDefault(layer, Status.NONE)) {
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

                "${layer.name} $message\n$details"
            }
    val customMessage = if (additionalMessage != null) "\n$additionalMessage" else ""

    val name = testName ?: getTestMethodNameFromEightIndex()

    return "'$name' test has failed.${customMessage}\n$failedDeclarationsMessage"
}
