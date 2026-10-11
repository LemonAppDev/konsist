package com.lemonappdev.konsist.core.verify

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.architecture.FileDependencies
import com.lemonappdev.konsist.core.architecture.FileDependency
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
import com.lemonappdev.konsist.core.verify.failure.DependsOnNotAllowedLayerDependencyFailure
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

    val fileDependencies = FileDependencies(files)
    val failedDependsOnLayers = getFailedDependsOnLayers(files, fileDependencies, layerDependenciesCore)
    val failedDependsOnNotAllowedLayers = getFailedDependsOnNotAllowedLayers(files, fileDependencies, layerDependenciesCore)
    val failedDoesNotDependsOnLayers = getFailedDoesNotDependsOnLayers(files, fileDependencies, layerDependenciesCore)
    val failedDependsOnNothing = getFailedDependsOnNothing(files, fileDependencies, layerDependenciesCore)

    if (failedDependsOnLayers.isNotEmpty() ||
        failedDependsOnNotAllowedLayers.isNotEmpty() ||
        failedDoesNotDependsOnLayers.isNotEmpty() ||
        failedDependsOnNothing.isNotEmpty()
    ) {
        val exceptionMessage =
            getExceptionMessage(
                additionalMessage,
                testName,
                failedDependsOnLayers,
                failedDependsOnNotAllowedLayers,
                failedDoesNotDependsOnLayers,
                failedDependsOnNothing,
            )

        throw KoAssertionFailedException(exceptionMessage)
    }
}

@Suppress("detekt.LongParameterList")
private fun getExceptionMessage(
    additionalMessage: String?,
    testName: String?,
    failedDependsOnLayers: List<DependsOnLayerDependencyFailure>,
    failedDependsOnNotAllowedLayers: List<DependsOnNotAllowedLayerDependencyFailure>,
    doesNotDependsOnLayerDependencyFailures: List<DoesNotDependsOnLayerDependencyFailure>,
    failedDependsOnNothing: List<DependsOnNothingDependencyFailure>,
): String {
    val failedDependsOnMessage = getFailedDependsOnMessage(failedDependsOnLayers)
    val failedDependsOnNotAllowedLayersMessage = getFailedDependsOnNotAllowedLayersMessage(failedDependsOnNotAllowedLayers)
    val failedDoesNotDependsOnLayersMessage = getFailedDoesNotDependsOnLayersMessage(doesNotDependsOnLayerDependencyFailures)
    val failedDependsOnNothingMessage = getFailedDependsOnNothingMessage(failedDependsOnNothing)

    check(
        failedDependsOnMessage != null ||
            failedDependsOnNotAllowedLayersMessage != null ||
            failedDoesNotDependsOnLayersMessage != null ||
            failedDependsOnNothingMessage != null,
    ) {
        "All failure messages cannot be null"
    }

    val messages =
        listOfNotNull(
            failedDependsOnMessage,
            failedDependsOnNotAllowedLayersMessage,
            failedDoesNotDependsOnLayersMessage,
            failedDependsOnNothingMessage,
        )

    val errorMessage = messages.joinToString("\n")
    val customMessage = if (additionalMessage != null) "\n$additionalMessage" else ""
    val localTestName = testName ?: KoTestNameContext.current ?: getTestMethodNameFromNinthIndex()
    val testMessage = localTestName?.let { "'$it' test has failed." } ?: "Test has failed."

    return "$testMessage ${customMessage}\n$errorMessage"
}

private fun getFailedDependsOnNothingMessage(failures: List<DependsOnNothingDependencyFailure>): String? =
    getFailureMessage(failures) {
        "'${it.layer.name}' layer should not depend on anything but has dependencies in files:"
    }

private fun getFailedDependsOnNotAllowedLayersMessage(failures: List<DependsOnNotAllowedLayerDependencyFailure>): String? =
    getFailureMessage(failures) {
        "'${it.layer1.name}' layer depends on '${it.notAllowedLayer.name}' layer, but this dependency is not declared. " +
            "Files that depend on '${it.notAllowedLayer.name}' layer:"
    }

private fun getFailedDoesNotDependsOnLayersMessage(failures: List<DoesNotDependsOnLayerDependencyFailure>): String? =
    getFailureMessage(failures) {
        "'${it.layer1.name}' layer should not depend on '${it.doesNotDependOnLayer.name}' layer but has dependencies in files:"
    }

private fun <T> getFailureMessage(
    failures: List<T>,
    getRootMessage: (T) -> String,
): String? {
    if (failures.isEmpty()) {
        return null
    }

    return failures.joinToString("\n\n") { failure ->
        val failedFiles =
            when (failure) {
                is DependsOnNothingDependencyFailure -> failure.failedFiles
                is DependsOnNotAllowedLayerDependencyFailure -> failure.failedFiles
                is DoesNotDependsOnLayerDependencyFailure -> failure.failedFiles
                else -> emptyMap()
            }

        val asciiTreeNodes =
            failedFiles.map { (file, dependencies) ->
                val children = dependencies.map { AsciiTreeNode(it, emptyList()) }

                AsciiTreeNode(file, children)
            }

        AsciiTreeCreator().invoke(
            AsciiTreeNode(
                getRootMessage(failure),
                asciiTreeNodes,
            ),
        )
    }
}

private fun getFailedDependsOnMessage(dependsOnLayerDependencyFailures: List<DependsOnLayerDependencyFailure>): String? {
    if (dependsOnLayerDependencyFailures.isEmpty()) {
        return null
    }

    return dependsOnLayerDependencyFailures.joinToString("\n") {
        "'${it.layer1.name}' layer is required to depend on '${it.dependsOnLayer.name}' layer (strict), but has no dependency on it."
    }
}

private fun getFailedDependsOnLayers(
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
    layerDependencies: LayerDependenciesCore,
): List<DependsOnLayerDependencyFailure> =
    layerDependencies.dependsOnDependencies
        .groupBy { it.layer1 }
        .flatMap { (layer1, dependencies) ->
            dependencies
                .mapNotNull { dep ->
                    dep.layer2?.let { layer2 ->
                        Triple(layer1, layer2, dep.strict)
                    }
                }.filter { (layer1, layer2, strict) ->
                    strict == true && !layer1.isDependentOn(layer2, files, fileDependencies)
                }.map { (layer1, layer2, _) ->
                    DependsOnLayerDependencyFailure(layer1, layer2)
                }
        }

private fun getFailedDependsOnNotAllowedLayers(
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
    layerDependencies: LayerDependenciesCore,
): List<DependsOnNotAllowedLayerDependencyFailure> {
    val allowedDependencies =
        layerDependencies.dependsOnDependencies
            .groupBy { it.layer1 }
            .mapValues { (_, dependencies) -> dependencies.mapNotNull { it.layer2 }.toSet() }

    return allowedDependencies.flatMap { (sourceLayer, allowedLayers) ->
        val notAllowedLayers = layerDependencies.layers - allowedLayers - sourceLayer

        notAllowedLayers.mapNotNull { notAllowedLayer ->
            val dependentFiles = sourceLayer.getDependentOnNotAllowedLayerFiles(notAllowedLayer, allowedLayers, files, fileDependencies)

            dependentFiles.takeIf { it.isNotEmpty() }?.let {
                DependsOnNotAllowedLayerDependencyFailure(
                    sourceLayer,
                    dependentFiles,
                    notAllowedLayer,
                )
            }
        }
    }
}

private fun getFailedDoesNotDependsOnLayers(
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
    layerDependencies: LayerDependenciesCore,
): List<DoesNotDependsOnLayerDependencyFailure> {
    val forbiddenDependencies =
        layerDependencies.doesNotDependsOnDependencies
            .groupBy { it.layer1 }
            .mapValues { (_, dependencies) -> dependencies.mapNotNull { it.layer2 }.toSet() }

    return forbiddenDependencies.flatMap { (sourceLayer, targetLayers) ->
        targetLayers.mapNotNull { targetLayer ->
            val dependentFiles = sourceLayer.getDependentOnFiles(targetLayer, files, fileDependencies)

            dependentFiles.takeIf { it.isNotEmpty() }?.let {
                DoesNotDependsOnLayerDependencyFailure(
                    sourceLayer,
                    dependentFiles,
                    targetLayer,
                )
            }
        }
    }
}

private fun getFailedDependsOnNothing(
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
    layerDependencies: LayerDependenciesCore,
): List<DependsOnNothingDependencyFailure> {
    val isolatedLayers =
        layerDependencies.dependsOnNothingDependencies
            .map { it.layer1 }
            .toSet()

    return isolatedLayers.mapNotNull { layer ->
        val dependentFiles = layer.getDependentOnAnyLayerFiles(files, fileDependencies, layerDependencies)

        dependentFiles.takeIf { it.isNotEmpty() }?.let {
            DependsOnNothingDependencyFailure(
                layer,
                dependentFiles,
            )
        }
    }
}

private fun Layer.isDependentOn(
    otherLayer: Layer,
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
): Boolean =
    files
        .withPackage(rootPackage)
        .flatMap { fileDependencies[it] }
        .any { dependency -> LocationUtil.residesInLocation(otherLayer.rootPackage, dependency.name) }

private fun Layer.getDependentOnFiles(
    otherLayer: Layer,
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
): Map<KoFileDeclaration, List<FileDependency>> =
    files
        .withPackage(rootPackage)
        .getFailedDependencies(fileDependencies) { dependency ->
            LocationUtil.residesInLocation(otherLayer.rootPackage, dependency.name)
        }

private fun Layer.getDependentOnNotAllowedLayerFiles(
    notAllowedLayer: Layer,
    allowedLayers: Set<Layer>,
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
): Map<KoFileDeclaration, List<FileDependency>> =
    files
        .withPackage(rootPackage)
        .getFailedDependencies(fileDependencies) { dependency ->
            // Dependency is not part of layer
            !LocationUtil.residesInLocation(rootPackage, dependency.name) &&
                // Dependency is not part of allowed layer
                allowedLayers.none { LocationUtil.residesInLocation(it.rootPackage, dependency.name) } &&
                LocationUtil.residesInLocation(notAllowedLayer.rootPackage, dependency.name)
        }

private fun Layer.getDependentOnAnyLayerFiles(
    files: List<KoFileDeclaration>,
    fileDependencies: FileDependencies,
    layerDependencies: LayerDependenciesCore,
): Map<KoFileDeclaration, List<FileDependency>> =
    files
        .withPackage(rootPackage)
        .getFailedDependencies(fileDependencies) { dependency ->
            // Dependency is not part of layer
            !LocationUtil.residesInLocation(rootPackage, dependency.name) &&
                // Dependency is part of other layer
                layerDependencies.layers.any { LocationUtil.residesInLocation(it.rootPackage, dependency.name) }
        }

/**
 * Maps each file to its dependencies (imports and fully qualified references) matching [isFailedDependency].
 * Files without such dependencies are skipped.
 */
private fun List<KoFileDeclaration>.getFailedDependencies(
    fileDependencies: FileDependencies,
    isFailedDependency: (FileDependency) -> Boolean,
): Map<KoFileDeclaration, List<FileDependency>> =
    associateWith { koFile -> fileDependencies[koFile].filter(isFailedDependency) }
        .filterValues { it.isNotEmpty() }
