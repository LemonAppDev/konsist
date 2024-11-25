package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to property delegate information.
 *
 * Implementations of this interface provide access to details about a property's delegate, which allows
 * custom behavior for property access and modification. Delegates can be used to delay property initialization,
 * handle caching, or synchronize access, among other uses.
 *
 * For example, using `by lazy`:
 *
 * ```kotlin
 * val lazyValue: String by lazy { "Hello, World!"}
 * ```
 */
interface KoPropertyDelegateProvider : KoDelegateProvider
