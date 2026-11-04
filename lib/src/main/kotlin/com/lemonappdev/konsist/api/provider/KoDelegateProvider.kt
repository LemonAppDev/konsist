package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to the delegate information.
 */
interface KoDelegateProvider : KoBaseProvider {
    /**
     * Name of the delegate.
     */
    val delegateName: String?

    /**
     * Determines whether the declaration has a `lazy` delegate with the given thread safety mode.
     *
     * ```kotlin
     * val sampleProperty by lazy { "value" }
     * val otherProperty by lazy(LazyThreadSafetyMode.NONE) { "value" }
     * ```
     *
     * A `lazy` delegate without an explicit mode uses [LazyThreadSafetyMode.SYNCHRONIZED].
     *
     * @param mode the thread safety mode of the `lazy` delegate (optional).
     * @return `true` if the declaration has a `lazy` delegate with the specified thread safety mode
     * (or any `lazy` delegate if [mode] is `null`), `false` otherwise.
     */
    fun hasLazyDelegate(mode: LazyThreadSafetyMode? = null): Boolean

    /**
     * Determines whether the declaration has a `Delegates.observable` delegate.
     *
     * ```kotlin
     * var sampleProperty by Delegates.observable("value") { _, _, _ -> }
     * ```
     */
    val hasObservableDelegate: Boolean

    /**
     * Determines whether the declaration has a `Delegates.vetoable` delegate.
     *
     * ```kotlin
     * var sampleProperty by Delegates.vetoable("value") { _, _, _ -> true }
     * ```
     */
    val hasVetoableDelegate: Boolean

    /**
     * Determines whether the declaration has a `Delegates.notNull` delegate.
     *
     * ```kotlin
     * var sampleProperty: String by Delegates.notNull()
     * ```
     */
    val hasNotNullDelegate: Boolean

    /**
     * Determines whether the declaration is delegated to another property (property reference delegate).
     *
     * ```kotlin
     * var sampleProperty by ::otherProperty
     * ```
     */
    val hasPropertyReferenceDelegate: Boolean

    /**
     * Determines whether the declaration has a delegate with the given name.
     *
     * @param delegateName the name of the delegate (optional).
     * @return `true` if the declaration has a delegate matching the specified name (or any delegate if [delegateName] is `null`),
     * `false` otherwise.
     */
    fun hasDelegate(delegateName: String? = null): Boolean
}
