package com.lemonappdev.konsist.api.provider

import kotlin.reflect.KClass

/**
 * Interface for providing tacit type information.
 *
 * This interface extends the `KoBaseProvider` and allows you to check whether a declaration or value
 * has an explicitly declared type or if the value contains a given type with parentheses '('.
 */
interface KoTacitTypeProvider : KoBaseProvider {
    /**
     * Determines whether the declaration has an explicitly declared type or if its value contains the
     * given type with parentheses '('.
     *
     * e.g.
     *
     * ```kotlin
     * val sampleProperty: Foo = someCollection.first() // hasTacitType("Foo") == true
     * val sampleProperty = Foo("some text") // hasTacitType("Foo") == true
     * val sampleProperty = someCollection.first() // hasTacitType("Foo") == false
     * ```
     *
     * @param type The type to check.
     * @return `true` if the type is explicitly declared or found with parentheses; `false` otherwise.
     */
    fun hasTacitType(type: String): Boolean

    /**
     * Determines whether the declaration has an explicitly declared type of the specified Kotlin class,
     * or if its value contains an instance of that class with parentheses '('.
     *
     * @param kClass The Kotlin class to check.
     * @return `true` if the type is explicitly declared or found with parentheses; `false` otherwise.
     */
    fun hasTacitTypeOf(kClass: KClass<*>): Boolean
}
