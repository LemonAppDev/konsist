package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides the declaration associated with this type.
 */
@Suppress("detekt.TooManyFunctions")
interface KoTypeDeclarationProvider : KoBaseProvider {
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
    fun asExternalTypeDeclaration(): KoExternalDeclaration?

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
    fun hasExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a external type declaration of the specified Kotlin class.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param kClass The Kotlin class representing the external type declaration to check for.
     * @return `true` if the type has a external type declaration (type defined outside the project codebase for e.g.
     * in external library) matching the specified KClass, `false` otherwise.
     */
    fun hasExternalTypeDeclarationOf(kClass: KClass<*>): Boolean
}
