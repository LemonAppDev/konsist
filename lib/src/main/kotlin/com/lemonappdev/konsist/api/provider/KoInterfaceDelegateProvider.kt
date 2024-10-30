package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to interface delegate information.
 *
 * Implementations of this interface offer access to details about an interface delegate, allowing classes
 * to delegate implementation of an interface to another object. Interface delegation is useful for reusing
 * functionality and composing behavior without inheritance.
 *
 * For example, in Kotlin, you can delegate an interface implementation to an object using the `by` keyword:
 *
 * ```kotlin
 * interface SampleSuperInterface
 *
 * class SampleClass(sampleProperty: SampleSuperInterface) : SampleSuperInterface by sampleProperty
 * ```
 */
interface KoInterfaceDelegateProvider : KoDelegateProvider
