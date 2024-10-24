package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides the declaration associated with this type.
 */
interface KoSourceDeclarationProvider : KoBaseProvider {
    /**
     * Represents the source declaration associated with this type.
     *
     * The `sourceDeclaration` property provides access to the declaration of the type within the Kotlin codebase.
     * It allows retrieving additional information about the declaration, such as its properties, functions,
     * annotations, and other relevant metadata.
     *
     * It points to an instance of [KoSourceDeclaration], which serves as the base interface for various types:
     *  - `KoClassDeclaration` represents class
     *  - `KoInterfaceDeclaration` represents interface
     *  - `KoObjectDeclaration` represents object
     *  - `KoTypeAliasDeclaration` represents type alias
     *  - `KoImportAliasDeclaration` represents import alias
     *  - `KoKotlinTypeDeclaration` represents Kotlin basic types and collections
     *  - `KoFunctionDeclaration` represents function type
     *  - `KoExternalDeclaration` represents declaration which is not defined in the project
     *
     * For import statements, `sourceDeclaration` refers to the declaration being imported.
     * Similarly, for an import alias, `sourceDeclaration` also refers to the declaration being imported,
     * allowing direct access to the original declaration.
     *
     * Example usage:
     *
     * ```kotlin
     * scope
     *     .properties()
     *     .types
     *     .assertTrue { it.isInterface }
     * ```
     */
    val sourceDeclaration: KoSourceDeclaration?

    /**
     * Determines whatever type has a specified source declaration.
     *
     * @param predicate The predicate function used to determine if a source declaration satisfies a condition.
     * @return `true` if the type has the specified source declaration, `false` otherwise.
     */
    fun hasSourceDeclaration(predicate: (KoSourceDeclaration) -> Boolean): Boolean

    /**
     * Whether type has a source declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source declaration to check for.
     * @return `true` if the type has a source declaration matching the specified KClass, `false` otherwise.
     */
    fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean
}
