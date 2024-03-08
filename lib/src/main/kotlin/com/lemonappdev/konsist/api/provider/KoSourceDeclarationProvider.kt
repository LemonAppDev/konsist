package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides the source declaration associated with this type.
 */
interface KoSourceDeclarationProvider : KoBaseProvider {
    /**
     * Represents the source declaration associated with this type.
     *
     * The `sourceDeclaration` property provides access to the declaration of the type within the Kotlin codebase.
     * It points to an instance of [KoBaseTypeDeclaration], which serves as the base interface for various types of
     * declarations in Kotlin.
     * This allows retrieving additional information about the declaration, such as its properties, functions,
     * annotations, and other relevant metadata.
     */
    val sourceDeclaration: KoBaseTypeDeclaration
}
