package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration

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

    /**
     * Represents the source class declaration associated with this type.
     */
    val sourceClass: KoClassDeclaration?

    /**
     * Represents the source object declaration associated with this type.
     */
    val sourceObject: KoObjectDeclaration?

    /**
     * Represents the source interface declaration associated with this type.
     */
    val sourceInterface: KoInterfaceDeclaration?

    /**
     * Represents the source type alias declaration associated with this type.
     */
    val sourceTypeAlias: KoTypeAliasDeclaration?

    /**
     * Represents the source import alias declaration associated with this type.
     */
    val sourceImportAlias: KoImportAliasDeclaration?

    /**
     * Represents the source Kotlin type declaration associated with this type.
     */
    val sourceKotlinType: KoKotlinTypeDeclaration?

    /**
     * Represents the source function type declaration associated with this type.
     */
    val sourceFunctionType: KoFunctionTypeDeclaration?

    /**
     * Represents the source external declaration associated with this type.
     */
    val sourceExternalType: KoExternalDeclaration?
}
