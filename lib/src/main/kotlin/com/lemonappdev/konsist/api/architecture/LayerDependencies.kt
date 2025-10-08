package com.lemonappdev.konsist.api.architecture

/**
 * Represents the dependency configuration for a layer within an architecture.
 *
 * This interface provides methods to define dependencies between layers in an architecture.
 */
interface LayerDependencies {
    /**
     * Establishes dependency relationships between layers in the architecture.
     *
     * Example:
     * ```kotlin
     * // Required dependency - Feature layer must depend on Domain layer
     * featureLayer.dependsOn(domainLayer, strict = true)
     *
     * // Optional dependency - Feature layer may depend on both Domain and Data layers
     * featureLayer.dependsOn(domainLayer, dataLayer, strict = false)
     * ```
     *
     * @receiver The source [Layer] that will have dependencies defined.
     * @param layer The primary target layer that the source layer will depend on.
     * @param layers Additional target layers that the source layer will depend on.
     * @param strict Controls the dependency enforcement:
     *              - true: MUST have dependencies - The source layer is required to have actual dependencies
     *                     on the target layer(s) in the codebase. If dependencies are missing, it will be reported as a violation.
     *              - false: MAY have dependencies (default) - The source layer is allowed to have dependencies
     *                     on the target layer(s), but it's not mandatory. No violations will be reported if dependencies are missing.
     */
    fun Layer.dependsOn(
        layer: Layer,
        vararg layers: Layer,
        strict: Boolean = false,
    ): Unit

    /**
     * Establishes dependency relationships from a source layer to multiple target layers.
     *
     * This function allows defining dependencies from a single source layer to multiple target layers
     * simultaneously using a Set of layers.
     *
     * Example:
     * ```kotlin
     * // Makes Feature layer depend on both Domain and Data layers
     * featureLayer.dependsOn(setOf(domainLayer, dataLayer))
     *
     * // Makes Feature layer require dependencies on both Domain and Data layers
     * featureLayer.dependsOn(setOf(domainLayer, dataLayer), strict = true)
     * ```
     *
     * @receiver The source [Layer] that will have dependencies defined.
     * @param layers Set of target layers that the source layer will depend on.
     * @param strict Controls the dependency enforcement:
     *              - true: MUST have dependencies - The source layer is required to have actual dependencies
     *                     on all target layers in the codebase. If any dependencies are missing, it will be reported as a violation.
     *              - false: MAY have dependencies (default) - The source layer is allowed to have dependencies
     *                     on the target layers, but it's not mandatory. No violations will be reported if dependencies are missing.
     */
    fun Layer.dependsOn(
        layers: Set<Layer>,
        strict: Boolean = false,
    ): Unit

    /**
     * Establishes dependency relationships from multiple source layers to target layer(s).
     *
     * This extension function allows defining dependencies for multiple layers at once, where each layer
     * in the collection will depend on all specified target layer(s).
     *
     * Example:
     * ```kotlin
     * // Makes both Feature and UI layers depend on Domain layer
     * setOf(featureLayer, uiLayer).dependsOn(domainLayer)
     *
     * // Makes Feature and UI layers depend on both Domain and Data layers, with required dependencies
     * setOf(featureLayer, uiLayer).dependsOn(domainLayer, dataLayer, strict = true)
     * ```
     *
     * @receiver The collection of source [Layer]s that will have dependencies defined.
     * @param layers Target layer(s) that each source layer will depend on.
     * @param strict Controls the dependency enforcement:
     *              - true: MUST have dependencies - Each source layer is required to have actual dependencies
     *                     on the target layer(s) in the codebase. If dependencies are missing, it will be reported as a violation.
     *              - false: MAY have dependencies (default) - The source layers are allowed to have dependencies
     *                     on the target layer(s), but it's not mandatory. No violations will be reported if dependencies are missing.
     */
    fun Collection<Layer>.dependsOn(
        vararg layers: Layer,
        strict: Boolean = false,
    ): Unit

    /**
     * Establishes dependency relationships from multiple source layers to multiple target layers.
     *
     * This function creates dependency rules where each layer in the source collection will depend on
     * all layers in the target set. This allows for defining multiple dependencies in bulk.
     *
     * Example:
     * ```kotlin
     * // Makes both Feature and UI layers depend on both Domain and Data layers
     * val sourceLayers = setOf(featureLayer, uiLayer)
     * val targetLayers = setOf(domainLayer, dataLayer)
     * sourceLayers.dependsOn(targetLayers)
     *
     * // With strict enforcement (required dependencies)
     * sourceLayers.dependsOn(targetLayers, strict = true)
     * ```
     * @receiver The collection of source [Layer]s that will have dependencies defined.
     * @param layers Set of target layers that each source layer will depend on.
     * @param strict Controls the dependency enforcement:
     *              - true: MUST have dependencies - Each source layer is required to have actual dependencies
     *                     on all target layers in the codebase. If dependencies are missing, it will be reported as a violation.
     *              - false: MAY have dependencies (default) - The source layers are allowed to have dependencies
     *                     on the target layers, but it's not mandatory. No violations will be reported if dependencies are missing.
     */
    fun Collection<Layer>.dependsOn(
        layers: Set<Layer>,
        strict: Boolean = false,
    ): Unit

    /**
     * Specifies forbidden dependencies between layers in the architecture.
     *
     * Example:
     * ```kotlin
     * // Domain layer must never depend on UI layer
     * domainLayer.doesNotDependOn(uiLayer, strict = true)
     *
     * // Domain layer should not depend on UI and Feature layers
     * domainLayer.doesNotDependOn(uiLayer, featureLayer, strict = false)
     * ```
     *
     * @receiver The source [Layer] for which dependencies will be forbidden.
     * @param layer The primary target layer that should not be depended on.
     * @param layers Additional target layers that should not be depended on.
     */
    fun Layer.doesNotDependOn(
        layer: Layer,
        vararg layers: Layer,
    ): Unit

    /**
     * Specifies forbidden dependencies from a source layer to multiple target layers.
     *
     * Example:
     * ```kotlin
     * // Domain layer must never depend on UI or Feature layers
     * domainLayer.doesNotDependOn(setOf(uiLayer, featureLayer))
     * ```
     *
     * @receiver The source [Layer] for which dependencies will be forbidden.
     * @param layers Set of target layers that should not be depended on.
     */
    fun Layer.doesNotDependOn(layers: Set<Layer>): Unit

    /**
     * Specifies forbidden dependencies from multiple source layers to a target layer.
     *
     * Example:
     * ```kotlin
     * // Domain and Data layers must never depend on UI layer
     * setOf(domainLayer, dataLayer).doesNotDependOn(uiLayer)
     * ```
     *
     * @receiver Collection of source [Layer]s for which dependencies will be forbidden.
     * @param layer The target layer that should not be depended on.
     * @param strict Controls the dependency enforcement:
     *              - true: MUST NOT have dependencies - Any dependencies between layers will be reported as violations.
     *              - false: SHOULD NOT have dependencies (default) - Dependencies are discouraged but allowed.
     */
    fun Collection<Layer>.doesNotDependOn(layer: Layer): Unit

    /**
     * Specifies forbidden dependencies from multiple source layers to multiple target layers.
     *
     * Example:
     * ```kotlin
     * // Domain and Data layers must never depend on UI and Feature layers
     * val sourceLayers = setOf(domainLayer, dataLayer)
     * val targetLayers = setOf(uiLayer, featureLayer)
     * sourceLayers.doesNotDependOn(targetLayers)
     * ```
     * @receiver Collection of source [Layer]s for which dependencies will be forbidden.
     * @param layers Set of target layers that should not be depended on.
     */
    fun Collection<Layer>.doesNotDependOn(layers: Set<Layer>): Unit

    /**
     * Specifies that the current layer must not have any dependencies.
     *
     * Example:
     * ```kotlin
     * // Domain layer must be completely independent
     * domainLayer.dependsOnNothing()
     * ```
     *
     * @receiver The [Layer] that should not have any dependencies.
     * @see include
     */
    fun Layer.dependsOnNothing(): Unit

    /**
     * Specifies that none of the layers in the collection should have any dependencies.
     *
     * Example:
     * ```kotlin
     * // Domain and Core layers must be completely independent
     * setOf(domainLayer, coreLayer).dependsOnNothing()
     * ```
     *
     * @receiver The collection of [Layer]s that should not have any dependencies.
     * @see include
     */
    fun Collection<Layer>.dependsOnNothing(): Unit

    /**
     * Includes a Layer in the architecture without specifying any dependencies.
     * Can be used in combination with dependsOnNothing() to specify that a layer
     * should be isolated from other layers.
     *
     * Example:
     * ```kotlin
     * // Include domain layer in architecture checks
     * domainLayer.include()
     * ```
     *
     * @receiver The [Layer] to be included in architecture checks.
     */
    fun Layer.include(): Unit

    /**
     * Includes multiple layers in the architecture without specifying any dependencies.
     * Can be used in combination with dependsOnNothing() to specify that layers
     * should be isolated from other layers.
     *
     * Example:
     * ```kotlin
     * // Include domain and core layers in architecture checks
     * setOf(domainLayer, coreLayer).include()
     * ```
     *
     * @receiver The collection of [Layer]s to be included in architecture checks.
     */
    fun Collection<Layer>.include(): Unit
}
