package com.lemonappdev.konsist.api.provider

interface KoFullyQualifiedNameProvider : KoBaseProvider {
    /**
     * Fully qualified name of the declaration.
     */
    val fullyQualifiedName: String
}
