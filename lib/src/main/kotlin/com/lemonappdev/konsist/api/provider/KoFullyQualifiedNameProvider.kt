package com.lemonappdev.konsist.api.provider

interface KoFullyQualifiedNameProvider : KoProvider {
    /**
     * Fully qualified name of the declaration.
     */
    val fullyQualifiedName: String
}
