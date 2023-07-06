package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin package declaration.
 */
interface KoPackageDeclaration :
    KoNamedDeclaration,
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * Qualified name of the package.
     */
    val qualifiedName: String

    /**
     * Whether the package has matching file path.
     */
    val hasMatchingFilePath: Boolean
}
