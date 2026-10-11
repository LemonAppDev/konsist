package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.verify.KoJUnitExtension
import com.lemonappdev.konsist.api.verify.KoKotestExtension

/**
 * An interface for asserting a KoArchitecture with its associated dependencies and configurations.
 */
interface KoArchitectureAssertion {
    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name displayed in error messages. By default, test name is derived from the JUnit
     *                 method name. For Kotest and JUnit 5 dynamic tests register [KoKotestExtension] or [KoJUnitExtension],
     *                 or pass the test name manually.
     *                 Architecture assertions do not support `@Suppress`; remove files from the scope instead.
     * @param allowFilesOutsideLayers Controls files that reside in no declared layer:
     *              - false: every file must reside in exactly one declared layer (default). Files that reside in no layer,
     *                     including files without a package, are reported as a violation.
     *              - true: files that reside in no layer are ignored.
     *              A file that resides in more than one layer always fails.
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        allowFilesOutsideLayers: Boolean = false,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name displayed in error messages. By default, test name is derived from the JUnit
     *                 method name. For Kotest and JUnit 5 dynamic tests register [KoKotestExtension] or [KoJUnitExtension],
     *                 or pass the test name manually.
     *                 Architecture assertions do not support `@Suppress`; remove files from the scope instead.
     * @param allowFilesOutsideLayers Controls files that reside in no declared layer:
     *              - false: every file must reside in exactly one declared layer (default). Files that reside in no layer,
     *                     including files without a package, are reported as a violation.
     *              - true: files that reside in no layer are ignored.
     *              A file that resides in more than one layer always fails.
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        allowFilesOutsideLayers: Boolean = false,
        layerDependenciesFunc: LayerDependencies,
    ): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name displayed in error messages. By default, test name is derived from the JUnit
     *                 method name. For Kotest and JUnit 5 dynamic tests register [KoKotestExtension] or [KoJUnitExtension],
     *                 or pass the test name manually.
     *                 Architecture assertions do not support `@Suppress`; remove files from the scope instead.
     * @param allowFilesOutsideLayers Controls files that reside in no declared layer:
     *              - false: every file must reside in exactly one declared layer (default). Files that reside in no layer,
     *                     including files without a package, are reported as a violation.
     *              - true: files that reside in no layer are ignored.
     *              A file that resides in more than one layer always fails.
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        allowFilesOutsideLayers: Boolean = false,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name displayed in error messages. By default, test name is derived from the JUnit
     *                 method name. For Kotest and JUnit 5 dynamic tests register [KoKotestExtension] or [KoJUnitExtension],
     *                 or pass the test name manually.
     *                 Architecture assertions do not support `@Suppress`; remove files from the scope instead.
     * @param allowFilesOutsideLayers Controls files that reside in no declared layer:
     *              - false: every file must reside in exactly one declared layer (default). Files that reside in no layer,
     *                     including files without a package, are reported as a violation.
     *              - true: files that reside in no layer are ignored.
     *              A file that resides in more than one layer always fails.
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        allowFilesOutsideLayers: Boolean = false,
        layerDependenciesFunc: LayerDependencies,
    ): Unit

    /**
     * Creates and returns a [LayerDependencies] instance representing the configured dependencies of the architecture,
     * defined as a function literal with receiver [LayerDependencies].
     *
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     * @return A [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun architecture(layerDependenciesFunc: LayerDependencies.() -> Unit): LayerDependencies
}
