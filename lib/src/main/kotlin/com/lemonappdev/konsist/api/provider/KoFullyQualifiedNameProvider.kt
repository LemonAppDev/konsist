package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its fully qualified name.
 */
interface KoFullyQualifiedNameProvider : KoBaseProvider {
    /**
     * Fully qualified name of the declaration.
     */
    val fullyQualifiedName: String?
}
