package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides the information associated with this type.
 */
@Suppress("detekt.TooManyFunctions")
interface KoDeclarationCastProvider : KoBaseProvider {
    /**
     * Determines whatever source declaration is a class.
     */
    val isClass: Boolean

    /**
     * Determines whatever source declaration is an object.
     */
    val isObject: Boolean

    /**
     * Determines whatever source declaration is a interface.
     */
    val isInterface: Boolean

    /**
     * Determines whatever source declaration is a type alias.
     */
    val isTypeAlias: Boolean

    /**
     * Determines whatever source declaration is import alias.
     */
    val isImportAlias: Boolean

    /**
     * Determines whatever type is a build in Kotlin type. It can be a basic Kotlin type
     * [Basic types](https://kotlinlang.org/docs/basic-types.html) or collection type
     * [Collections overview] (https://kotlinlang.org/docs/collections-overview.html#collection).
     */
    val isKotlinType: Boolean

    /**
     * Determines whatever type is a Kotlin stdlib basic type
     * [Basic types](https://kotlinlang.org/docs/basic-types.html)
     */
    val isKotlinBasicType: Boolean

    /**
     * Determines whatever type is a Kotlin stdlib Collection type
     * [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
     */
    val isKotlinCollectionType: Boolean

    /**
     * Determines whatever source declaration is a type parameter.
     */
    val isTypeParameter: Boolean

    /**
     * Determines whatever source declaration is an external type.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     */
    val isExternal: Boolean

    /**
     * Represents the class declaration associated with this type.
     *
     * @return the class declaration associated with this type.
     */
    fun asClassDeclaration(): KoClassDeclaration?

    /**
     * Represents the object declaration associated with this type.
     *
     * @return the object declaration associated with this type.
     */
    fun asObjectDeclaration(): KoObjectDeclaration?

    /**
     * Represents the interface declaration associated with this type.
     *
     * @return the interface declaration associated with this type.
     */
    fun asInterfaceDeclaration(): KoInterfaceDeclaration?

    /**
     * Represents the type alias declaration associated with this type.
     *
     * @return the type alias declaration associated with this type.
     */
    fun asTypeAliasDeclaration(): KoTypeAliasDeclaration?

    /**
     * Represents the import alias declaration associated with this type.
     *
     * @return the import alias declaration associated with this type.
     */
    fun asImportAliasDeclaration(): KoImportAliasDeclaration?

    /**
     * Represents the Kotlin type declaration associated with this type.
     *
     * @return the Kotlin type declaration associated with this type.
     */
    fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration?

    /**
     * Represents the Kotlin basic type declaration associated with this type.
     *
     * @return the Kotlin basic type declaration associated with this type.
     */
    fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration?

    /**
     * Represents the Kotlin collection type declaration associated with this type.
     *
     * @return the Kotlin collection type declaration associated with this type.
     */
    fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration?

    /**
     * Represents the type parameter declaration associated with this type.
     *
     * @return the type parameter declaration associated with this type.
     */
    fun asTypeParameterDeclaration(): KoTypeParameterDeclaration?

    /**
     * Represents the external declaration associated with this type.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @return the external declaration associated with this type.
     */
    fun asExternalDeclaration(): KoExternalDeclaration?

    /**
     * Whether type has a specified class declaration.
     *
     * @param predicate The predicate function used to determine if a class declaration satisfies a condition.
     * @return `true` if the type has the specified class declaration (or any class declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a class declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the class declaration to check for.
     * @return `true` if the type has a class declaration matching the specified KClass, `false` otherwise.
     */
    fun hasClassDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified object declaration.
     *
     * @param predicate The predicate function used to determine if a object declaration satisfies a condition.
     * @return `true` if the type has the specified object declaration (or any object declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a object declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the object declaration to check for.
     * @return `true` if the type has a object declaration matching the specified KClass, `false` otherwise.
     */
    fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified interface declaration.
     *
     * @param predicate The predicate function used to determine if a interface declaration satisfies a condition.
     * @return `true` if the type has the specified interface declaration (or any interface declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a interface declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the interface declaration to check for.
     * @return `true` if the type has a interface declaration matching the specified KClass, `false` otherwise.
     */
    fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified type alias declaration.
     *
     * @param predicate The predicate function used to determine if a type alias declaration satisfies a condition.
     * @return `true` if the type has the specified type alias declaration (or any type alias declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a specified import alias declaration.
     *
     * @param predicate The predicate function used to determine if a import alias declaration satisfies a condition.
     * @return `true` if the type has the specified import alias declaration (or any import alias declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a specified kotlin type declaration.
     *
     * @param predicate The predicate function used to determine if a kotlin type declaration satisfies a condition.
     * @return `true` if the type has the specified kotlin type declaration (or any kotlin type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a kotlin type declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the kotlin type declaration to check for.
     * @return `true` if the type has a kotlin type declaration matching the specified KClass, `false` otherwise.
     */
    fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified kotlin basic type declaration.
     *
     * @param predicate The predicate function used to determine if a kotlin basic type declaration satisfies a condition.
     * @return `true` if the type has the specified kotlin basic type declaration (or any kotlin basic type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a kotlin basic type declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the kotlin basic type declaration to check for.
     * @return `true` if the type has a kotlin basic type declaration matching the specified KClass, `false` otherwise.
     */
    fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified kotlin collection type declaration.
     *
     * @param predicate The predicate function used to determine if a kotlin collection type declaration satisfies a condition.
     * @return `true` if the type has the specified kotlin collection type declaration (or any kotlin collection type
     * declaration if [predicate] is `null`), `false` otherwise.
     */
    fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a kotlin collection type declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the kotlin collection type declaration to check for.
     * @return `true` if the type has a kotlin collection type declaration matching the specified KClass, `false` otherwise.
     */
    fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified type parameter declaration.
     *
     * @param predicate The predicate generic used to determine if a type parameter declaration satisfies a condition.
     * @return `true` if the type has the specified type parameter declaration (or any parameter type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a specified external type declaration.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param predicate The predicate external used to determine if a external type declaration satisfies a condition.
     * @return `true` if the type has the specified external type declaration (or any external type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a external type declaration of the specified Kotlin class.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param kClass The Kotlin class representing the external type declaration to check for.
     * @return `true` if the type has a external type declaration (type defined outside the project codebase for e.g.
     * in external library) matching the specified KClass, `false` otherwise.
     */
    fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean
}
