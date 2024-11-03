package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.architecture.KoArchitectureFiles
import com.lemonappdev.konsist.core.architecture.KoArchitectureScope
import com.lemonappdev.konsist.core.architecture.LayerDependenciesCore
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeCreator
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeNode
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.exception.KoException
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.util.LocationUtil
import com.lemonappdev.konsist.core.verify.failure.DependsOnLayerDependencyFailure
import com.lemonappdev.konsist.core.verify.failure.DependsOnNothingDependencyFailure
import com.lemonappdev.konsist.core.verify.failure.DoesNotDependsOnLayerDependencyFailure

@Suppress("detekt.ThrowsCount")
internal fun KoArchitectureFiles.assert(
    additionalMessage: String?,
    testName: String?,
): Unit {
    try {
        val layerDependenciesCore = layerDependencies as LayerDependenciesCore

        assertCommon(
            files,
            layerDependenciesCore,
            additionalMessage,
            testName,
        )
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
        val layerDependenciesCore = layerDependencies as LayerDependenciesCore

        assertCommon(
            koScope.files,
            layerDependenciesCore,
            additionalMessage,
            testName,
        )
    } catch (e: KoException) {
        throw e
    } catch (
        @Suppress("detekt.TooGenericExceptionCaught") e: Exception,
    ) {
        throw KoInternalException(e.message.orEmpty(), e)
    }
}

@Suppress("detekt.ThrowsCount")
private fun assertCommon(
    files: List<KoFileDeclaration>,
    layerDependenciesCore: LayerDependenciesCore,
    additionalMessage: String?,
    testName: String?,
) {
    layerDependenciesCore.checkEmptyLayersDependencies()
    layerDependenciesCore.checkLayersWithoutFiles(files)

    val failedDependsOnLayers = getFailedDependsOnLayers(files, layerDependenciesCore)
    val failedDoesNotDependsOnLayers = getFailedDoesNotDependsOnLayers(files, layerDependenciesCore)
    val failedDependsOnNothing = getFailedDependsOnNothing(files, layerDependenciesCore)

    if (failedDependsOnLayers.isNotEmpty() || failedDoesNotDependsOnLayers.isNotEmpty() || failedDependsOnNothing.isNotEmpty()) {
        val exceptionMessage =
            getExceptionMessage(
                additionalMessage,
                testName,
                failedDependsOnLayers,
                failedDoesNotDependsOnLayers,
                failedDependsOnNothing,
            )

        throw KoAssertionFailedException(exceptionMessage)
    }
}

private fun getExceptionMessage(
    additionalMessage: String?,
    testName: String?,
    failedDependsOnLayers: List<DependsOnLayerDependencyFailure>,
    doesNotDependsOnLayerDependencyFailures: List<DoesNotDependsOnLayerDependencyFailure>,
    failedDependsOnNothing: List<DependsOnNothingDependencyFailure>,
): String {
    val failedDependsOnMessage = getFailedDependsOnMessage(failedDependsOnLayers)
    val failedDoesNotDependsOnLayersMessage = getFailedDoesNotDependsOnLayersMessage(doesNotDependsOnLayerDependencyFailures)
    val failedDependsOnNothingMessage = getFailedDependsOnNothingMessage(failedDependsOnNothing)

    check(
        failedDependsOnMessage != null ||
            failedDoesNotDependsOnLayersMessage != null ||
            failedDependsOnNothingMessage != null,
    ) {
        "All failure messages cannot be null"
    }

    val messages =
        listOfNotNull(
            failedDependsOnMessage,
            failedDoesNotDependsOnLayersMessage,
            failedDependsOnNothingMessage,
        )

    val errorMessage = messages.joinToString("\n")
    val customMessage = if (additionalMessage != null) "\n$additionalMessage" else ""
    val localTestName = testName ?: getTestMethodNameFromNinthIndex()

    return "'$localTestName' test has failed. ${customMessage}\n$errorMessage"
}

private fun getFailedDependsOnNothingMessage(failedDependsOnNothing: List<DependsOnNothingDependencyFailure>): String? {
    if (failedDependsOnNothing.isEmpty()) {
        return null
    }

    return failedDependsOnNothing.joinToString("\n\n") { failure ->
        val headerNode =
            AsciiTreeNode(
                string = "'${failure.layer.name}' layer should not depend on anything but has dependencies in files:",
            )

        val fileNodes =
            failure.failedFiles.map { file ->
                val importNodes =
                    file.imports.map { import ->
                        AsciiTreeNode(
                            string = "import ${import.name}",
                            children = emptyList(),
                        )
                    }

                AsciiTreeNode(
                    string = "file ${file.path}",
                    children = importNodes,
                )
            }

        val rootNode =
            AsciiTreeNode(
                string = headerNode.string,
                children = fileNodes,
            )

        AsciiTreeCreator().invoke(rootNode)
    }
}

private fun getFailedDependsOnMessage(dependsOnLayerDependencyFailures: List<DependsOnLayerDependencyFailure>): String? {
    if (dependsOnLayerDependencyFailures.isEmpty()) {
        return null
    }

    return dependsOnLayerDependencyFailures.joinToString("\n") {
        "Layer '${it.layer1.name}' does not depends on '${it.dependsOnLayer.name}' layer."
    }
}

private fun getFailedDoesNotDependsOnLayersMessage(
    doesNotDependsOnLayerDependencyFailures: List<DoesNotDependsOnLayerDependencyFailure>,
): String? {
    if (doesNotDependsOnLayerDependencyFailures.isEmpty()) {
        return null
    }

    return doesNotDependsOnLayerDependencyFailures.joinToString("\n\n") { failure ->
        val headerNode =
            AsciiTreeNode(
                string =
                    "'${failure.layer1.name}' layer does not depends on '${failure.doesNotDependOnLayer.name}' layer failed. " +
                        "Files that depend on '${failure.doesNotDependOnLayer.name}' layer:",
            )

        val fileNodes =
            failure.failedFiles.map { file ->
                val importNodes =
                    file.imports.map { import ->
                        AsciiTreeNode(
                            string = "import ${import.name}",
                            children = emptyList(),
                        )
                    }

                AsciiTreeNode(
                    string = "file ${file.path}",
                    children = importNodes,
                )
            }

        val rootNode =
            AsciiTreeNode(
                string = headerNode.string,
                children = fileNodes,
            )

        AsciiTreeCreator().invoke(rootNode)
    }
}

private fun getFailedDependsOnLayers(
    files: List<KoFileDeclaration>,
    layerDependencies: LayerDependenciesCore,
): List<DependsOnLayerDependencyFailure> {
    val failedLayerDependencies = mutableListOf<DependsOnLayerDependencyFailure>()

    layerDependencies
        .dependsOnDependencies
        .forEach { (layer, otherLayers) ->
            otherLayers.forEach { otherLayer ->
                if (!layer.isDependentOn(otherLayer, files)) {
                    failedLayerDependencies += DependsOnLayerDependencyFailure(layer, otherLayer)
                }
            }
        }

    return failedLayerDependencies
}

private fun getFailedDoesNotDependsOnLayers(
    files: List<KoFileDeclaration>,
    layerDependencies: LayerDependenciesCore,
): List<DoesNotDependsOnLayerDependencyFailure> {
    val failedLayerDependencies = mutableListOf<DoesNotDependsOnLayerDependencyFailure>()

    layerDependencies
        .doesNotDependsOnDependencies
        .forEach { (layer, otherLayers) ->
            otherLayers.forEach { otherLayer ->
                val dependOnFiles = layer.getDependentOnFiles(otherLayer, files)

                if (dependOnFiles.isNotEmpty()) {
                    failedLayerDependencies +=
                        DoesNotDependsOnLayerDependencyFailure(
                            layer,
                            dependOnFiles,
                            otherLayer,
                        )
                }
            }
        }

    return failedLayerDependencies
}

private fun getFailedDependsOnNothing(
    files: List<KoFileDeclaration>,
    layerDependencies: LayerDependenciesCore,
): List<DependsOnNothingDependencyFailure> {
    val failedLayerDependencies = mutableListOf<DependsOnNothingDependencyFailure>()

    layerDependencies
        .dependsOnNothingDependencies
        .forEach { layer ->
            val dependentFiles = layer.getDependentOnAnythingFiles(files)

            if (dependentFiles.isNotEmpty()) {
                failedLayerDependencies +=
                    DependsOnNothingDependencyFailure(
                        layer,
                        dependentFiles,
                    )
            }
        }

    return failedLayerDependencies
}

private fun Layer.isDependentOn(
    otherLayer: Layer,
    files: List<KoFileDeclaration>,
): Boolean {
    val layerFiles = files.withPackage(rootPackage)

    val isDependentOn =
        layerFiles
            .flatMap { it.imports }
            .any { import -> LocationUtil.resideInLocation(otherLayer.rootPackage, import.name) }

    return isDependentOn
}

private fun Layer.getDependentOnFiles(
    otherLayer: Layer,
    files: List<KoFileDeclaration>,
): List<KoFileDeclaration> {
    val layerFiles = files.withPackage(rootPackage)

    val dependOnFiles =
        layerFiles
            .mapNotNull { koFile ->
                val imports = koFile.imports

                val hasImportToOtherLayer = imports.any { import -> LocationUtil.resideInLocation(otherLayer.rootPackage, import.name) }

                if (hasImportToOtherLayer) {
                    koFile
                } else {
                    null
                }
            }

    return dependOnFiles
}

private fun Layer.getDependentOnAnythingFiles(files: List<KoFileDeclaration>): List<KoFileDeclaration> {
    val layerFiles = files.withPackage(rootPackage)

    return layerFiles
        .mapNotNull { koFile ->
            val imports =
                koFile
                    .imports
                    .filterNot { LocationUtil.resideInLocation(rootPackage, it.name) }

            if (imports.isNotEmpty()) {
                koFile
            } else {
                null
            }
        }
}
