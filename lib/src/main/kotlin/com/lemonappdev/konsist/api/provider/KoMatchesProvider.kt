package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it matches a specified element.
 */
interface KoMatchesProvider : KoBaseProvider {
    /**
     *  Determines whether the given [element] matches to declaration.
     *
     * @param element The element to be matched, typically represented as a `String`.
     * @return `true` if the [element] matches the declaration, `false` otherwise.
     */
    fun matches(element: String): Boolean
}
