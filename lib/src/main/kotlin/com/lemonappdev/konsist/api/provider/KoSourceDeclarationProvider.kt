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
import kotlin.reflect.KClass

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

    /**
     * Determines whatever type has a specified source declaration.
     *
     * @param predicate The predicate function used to determine if a source declaration satisfies a condition.
     * @return `true` if the type has the specified source declaration, `false` otherwise.
     */
    fun hasSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean

    /**
     * Whether type has a source declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source declaration to check for.
     * @return `true` if the type has a source declaration matching the specified KClass, `false` otherwise.
     */
    fun hasSourceDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified source class.
     *
     * @param predicate The predicate function used to determine if a source class satisfies a condition.
     * @return `true` if the type has the specified source class (or any source class if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasSourceClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a source class of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source class to check for.
     * @return `true` if the type has a source class matching the specified KClass, `false` otherwise.
     */
    fun hasSourceClassOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified source object.
     *
     * @param predicate The predicate function used to determine if a source object satisfies a condition.
     * @return `true` if the type has the specified source object (or any source object if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasSourceObject(predicate: ((KoObjectDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a source object of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source object to check for.
     * @return `true` if the type has a source object matching the specified KClass, `false` otherwise.
     */
    fun hasSourceObjectOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified source interface.
     *
     * @param predicate The predicate function used to determine if a source interface satisfies a condition.
     * @return `true` if the type has the specified source interface (or any source interface if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasSourceInterface(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a source interface of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source interface to check for.
     * @return `true` if the type has a source interface matching the specified KClass, `false` otherwise.
     */
    fun hasSourceInterfaceOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified source type alias.
     *
     * @param predicate The predicate function used to determine if a source type alias satisfies a condition.
     * @return `true` if the type has the specified source type alias (or any source type alias if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasSourceTypeAlias(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a specified source import alias.
     *
     * @param predicate The predicate function used to determine if a source import alias satisfies a condition.
     * @return `true` if the type has the specified source import alias (or any source import alias if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasSourceImportAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a specified source kotlin type.
     *
     * @param predicate The predicate function used to determine if a source kotlin type satisfies a condition.
     * @return `true` if the type has the specified source kotlin type (or any source kotlin type if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasSourceKotlinType(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a source kotlin type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source kotlin type to check for.
     * @return `true` if the type has a source kotlin type matching the specified KClass, `false` otherwise.
     */
    fun hasSourceKotlinTypeOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified source function type.
     *
     * @param predicate The predicate function used to determine if a source function type satisfies a condition.
     * @return `true` if the type has the specified source function type (or any source function type if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasSourceFunctionType(predicate: ((KoFunctionTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a specified source external type.
     *
     * @param predicate The predicate external used to determine if a source external type satisfies a condition.
     * @return `true` if the type has the specified source external type (or any source external type if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasSourceExternalType(predicate: ((KoExternalDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a source external type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the source external type to check for.
     * @return `true` if the type has a source external type matching the specified KClass, `false` otherwise.
     */
    fun hasSourceExternalTypeOf(kClass: KClass<*>): Boolean
}
