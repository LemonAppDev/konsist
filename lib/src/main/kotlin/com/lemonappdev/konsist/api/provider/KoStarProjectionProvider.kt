package com.lemonappdev.konsist.api.provider

/**
 * Represents a provider that indicates whether a declaration is a star projection.
 *
 * A star projection (`*`) is a wildcard used in Kotlin's generic type system to represent
 * any type, providing flexibility in generic contexts by allowing the use of unknown types
 * or types that can be substituted by any type argument.
 *
 * Example usage:
 * - A star projection is often seen in generic collections like `List<*>`, where the
 *   exact type is not known or not important.
 */
interface KoStarProjectionProvider : KoBaseProvider {
    /**
     * Determines whether the source declaration is a star projection.
     * A star projection is a placeholder that represents any type in a generic context, allowing for flexible type constraints.
     */
    val isStarProjection: Boolean
}
