package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.architecture.DependencyRulesCore
import com.lemonappdev.konsist.core.architecture.KoArchitectureFiles
import com.lemonappdev.konsist.core.architecture.KoArchitectureScope
import com.lemonappdev.konsist.core.architecture.Status
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

private fun validateAllLayersAreValid(files: List<KoFileDeclaration>, dependencyRules: DependencyRulesCore) {
    val isAllLayersValid = dependencyRules.allLayers
        .all {
            files
                .withPackage(it.definedBy)
                .isNotEmpty()
        }

    if (!isAllLayersValid) {
        val layer = dependencyRules
            .allLayers
            .first {
                files
                    .withPackage(it.definedBy)
                    .isEmpty()
            }
        throw KoPreconditionFailedException("Layer ${layer.name} doesn't contain any files.")
    }
}

private fun validateLayersOnDependencyRules(dependencyRules: DependencyRulesCore) {
    if (dependencyRules.allLayers.isEmpty()) {
        throw KoPreconditionFailedException("Architecture doesn't contain layers or dependencies.")
    }
}

private fun validateLayersContainingFailedFiles(files: List<KoFileDeclaration>, dependencyRules: DependencyRulesCore) {
    val failedFiles = mutableListOf<FailedFiles>()

    dependencyRules
        .dependencies
        .forEach { (layer, layers) ->
            val otherLayers = (dependencyRules.allLayers - layers)

            files
                .withPackage(layer.definedBy)
                .onEach {
                    otherLayers.forEach { otherLayer ->
                        val imports = it.imports.filter { import ->
                            LocationUtil.resideInLocation(otherLayer.definedBy, import.name)
                        }

                        if (imports.isNotEmpty()) {
                            failedFiles += FailedFiles(it.path, layer, otherLayer.name, imports)
                        }
                    }
                }
        }

    if (failedFiles.isNotEmpty()) {
        throw KoCheckFailedException(
            getCheckFailedMessages(
                failedFiles.distinct(),
                dependencyRules.dependencies,
                dependencyRules.statuses,
            ),
        )
    }
}

private fun validateLayers(files: List<KoFileDeclaration>, dependencyRules: DependencyRulesCore) {
    validateLayersOnDependencyRules(dependencyRules = dependencyRules)
    validateAllLayersAreValid(files = files, dependencyRules = dependencyRules)
    validateLayersContainingFailedFiles(files = files, dependencyRules = dependencyRules)
}

internal fun KoArchitectureFiles.assert() {
    val dependencyRules = this.dependencyRules as DependencyRulesCore
    validateLayers(this.files, dependencyRules)
}

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureScope.assert() {
    try {
        val files = this.koScope.files
        val dependencyRules = this.dependencyRules as DependencyRulesCore
        validateLayers(files, dependencyRules)
     } catch (e: KoException) {
        throw e
    } catch (@Suppress("detekt.TooGenericExceptionCaught") e: Exception) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
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
): String {
    val failedDeclarationsMessage = failedFiles
        .map { it.resideInLayer }
        .joinToString("\n") { layer ->
            val details = failedFiles
                .filter { it.resideInLayer == layer }
                .joinToString(separator = "\n") {
                    "A file ${it.path} in a ${it.resideInLayer.name} layer depends on ${it.failedLayer} layer, imports:\n${
                        it.imports.joinToString(
                            separator = "\n",
                        ) { import -> "\t${import.name} (${import.location})" }
                    }"
                }

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

            "${layer.name} $message\n$details"
        }

    return "'${getTestMethodNameFromSeventhIndex()}' test has failed.\n$failedDeclarationsMessage"
}
