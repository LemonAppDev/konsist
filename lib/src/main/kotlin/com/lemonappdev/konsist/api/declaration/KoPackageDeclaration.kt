package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin package declaration.
 */
interface KoPackageDeclaration : KoNamedDeclaration {
    /**
     * Qualified name of the package.
     */
    val qualifiedName: String
}
