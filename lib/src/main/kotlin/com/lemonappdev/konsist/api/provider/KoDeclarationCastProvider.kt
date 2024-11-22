package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides the information associated with this declaration.
 */
@Suppress("detekt.TooManyFunctions")
interface KoDeclarationCastProvider : KoBaseProvider, KoSourceDeclaration {
    /**
     * Determines whatever declaration is a class.
     */
    val isClass: Boolean

    /**
     * Determines whatever declaration is an object.
     */
    val isObject: Boolean

    /**
     * Determines whatever declaration is an interface.
     */
    val isInterface: Boolean

    /**
     * Determines whatever declaration is a class or an object.
     */
    val isClassOrObject: Boolean

    /**
     * Determines whatever declaration is a class or an interface.
     */
    val isClassOrInterface: Boolean

    /**
     * Determines whatever declaration is an interface or an object.
     */
    val isInterfaceOrObject: Boolean

    /**
     * Determines whatever declaration is a class, an interface or an object.
     */
    val isClassOrInterfaceOrObject: Boolean

    /**
     * Determines whatever declaration is a type alias.
     */
    val isTypeAlias: Boolean

    /**
     * Determines whatever declaration is import alias.
     */
    val isImportAlias: Boolean

    /**
     * Determines whatever declaration is a build in Kotlin type. It can be a basic Kotlin type
     * [Basic types](https://kotlinlang.org/docs/basic-types.html) or collection type
     * [Collections overview] (https://kotlinlang.org/docs/collections-overview.html#collection).
     */
    val isKotlinType: Boolean

    /**
     * Determines whatever declaration is a Kotlin stdlib basic type
     * [Basic types](https://kotlinlang.org/docs/basic-types.html)
     */
    val isKotlinBasicType: Boolean

    /**
     * Determines whatever declaration is a Kotlin stdlib Collection type
     * [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
     */
    val isKotlinCollectionType: Boolean

    /**
     * Determines whatever declaration is a type parameter.
     */
    val isTypeParameter: Boolean

    /**
     * Determines whatever declaration is an external.
     * An external declaration refers to a declaration that is defined outside the project's codebase. for e.g. in external library.
     */
    val isExternal: Boolean

    /**
     * Determines whatever source declaration is an external type.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("isExternal"))
    val isExternalType: Boolean

    /**
     * Determines whatever declaration is a function.
     */
    val isFunction: Boolean

    /**
     * Determines whatever declaration is a property.
     */
    val isProperty: Boolean

    /**
     * Represents the class declaration associated with this declaration.
     *
     * @return the class declaration associated with this declaration.
     */
    fun asClassDeclaration(): KoClassDeclaration?

    /**
     * Represents the object declaration associated with this declaration.
     *
     * @return the object declaration associated with this declaration.
     */
    fun asObjectDeclaration(): KoObjectDeclaration?

    /**
     * Represents the interface declaration associated with this declaration.
     *
     * @return the interface declaration associated with this declaration.
     */
    fun asInterfaceDeclaration(): KoInterfaceDeclaration?

    /**
     * Represents the class or object declaration associated with this declaration.
     *
     * @return the class or object declaration associated with this declaration.
     */
    fun asClassOrObjectDeclaration(): KoClassAndObjectDeclaration?

    /**
     * Represents the class or interface declaration associated with this declaration.
     *
     * @return the class or interface declaration associated with this declaration.
     */
    fun asClassOrInterfaceDeclaration(): KoClassAndInterfaceDeclaration?

    /**
     * Represents the interface or object declaration associated with this declaration.
     *
     * @return the interface or object declaration associated with this declaration.
     */
    fun asInterfaceOrObjectDeclaration(): KoInterfaceAndObjectDeclaration?

    /**
     * Represents the class, interface or object declaration associated with this declaration.
     *
     * @return the class, interface or object declaration associated with this declaration.
     */
    fun asClassOrInterfaceOrObjectDeclaration(): KoClassAndInterfaceAndObjectDeclaration?

    /**
     * Represents the declaration alias declaration associated with this declaration.
     *
     * @return the declaration alias declaration associated with this declaration.
     */
    fun asTypeAliasDeclaration(): KoTypeAliasDeclaration?

    /**
     * Represents the import alias declaration associated with this declaration.
     *
     * @return the import alias declaration associated with this declaration.
     */
    fun asImportAliasDeclaration(): KoImportAliasDeclaration?

    /**
     * Represents the Kotlin type declaration associated with this declaration.
     *
     * @return the Kotlin type declaration associated with this declaration.
     */
    fun asKotlinTypeDeclaration(): KoKotlinTypeDeclaration?

    /**
     * Represents the Kotlin basic type declaration associated with this declaration.
     *
     * @return the Kotlin basic type declaration associated with this declaration.
     */
    fun asKotlinBasicTypeDeclaration(): KoKotlinTypeDeclaration?

    /**
     * Represents the Kotlin collection type declaration associated with this declaration.
     *
     * @return the Kotlin collection type declaration associated with this declaration.
     */
    fun asKotlinCollectionTypeDeclaration(): KoKotlinTypeDeclaration?

    /**
     * Represents the declaration parameter declaration associated with this declaration.
     *
     * @return the declaration parameter declaration associated with this declaration.
     */
    fun asTypeParameterDeclaration(): KoTypeParameterDeclaration?

    /**
     * Represents the external declaration associated with this declaration.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @return the external declaration associated with this declaration.
     */
    fun asExternalDeclaration(): KoExternalDeclaration?

    /**
     * Represents the external declaration associated with this declaration.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @return the external declaration associated with this declaration.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("asExternal"))
    fun asExternalTypeDeclaration(): KoExternalDeclaration?

    /**
     * Represents the function declaration associated with this declaration.
     *
     * @return the function declaration associated with this declaration.
     */
    fun asFunctionDeclaration(): KoFunctionDeclaration?

    /**
     * Represents the property declaration associated with this declaration.
     *
     * @return the property declaration associated with this declaration.
     */
    fun asPropertyDeclaration(): KoPropertyDeclaration?

    /**
     * Whether declaration has a specified class declaration.
     *
     * @param predicate The predicate function used to determine if a class declaration satisfies a condition.
     * @return `true` if the declaration has the specified class declaration (or any class declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasClassDeclaration(predicate: ((KoClassDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a class declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the class declaration to check for.
     * @return `true` if the declaration has a class declaration matching the specified KClass, `false` otherwise.
     */
    fun hasClassDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified object declaration.
     *
     * @param predicate The predicate function used to determine if a object declaration satisfies a condition.
     * @return `true` if the declaration has the specified object declaration (or any object declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasObjectDeclaration(predicate: ((KoObjectDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a object declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the object declaration to check for.
     * @return `true` if the declaration has a object declaration matching the specified KClass, `false` otherwise.
     */
    fun hasObjectDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified interface declaration.
     *
     * @param predicate The predicate function used to determine if an interface declaration satisfies a condition.
     * @return `true` if the declaration has the specified interface declaration (or any interface declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasInterfaceDeclaration(predicate: ((KoInterfaceDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has an interface declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the interface declaration to check for.
     * @return `true` if the declaration has an interface declaration matching the specified KClass, `false` otherwise.
     */
    fun hasInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified class or object declaration.
     *
     * @param predicate The predicate function used to determine if class or object declaration satisfies a condition.
     * @return `true` if the declaration has the specified class or object declaration (or any class or object
     * declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasClassOrObjectDeclaration(predicate: ((KoClassAndObjectDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a class or an object declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the class or object declaration to check for.
     * @return `true` if the declaration has a class or an object declaration matching the specified KClass, `false` otherwise.
     */
    fun hasClassOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified class or interface declaration.
     *
     * @param predicate The predicate function used to determine if class or interface declaration satisfies a condition.
     * @return `true` if the declaration has the specified class or interface declaration (or any class or interface
     * declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasClassOrInterfaceDeclaration(predicate: ((KoClassAndInterfaceDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a class or an interface declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the class or interface declaration to check for.
     * @return `true` if the declaration has a class or an interface declaration matching the specified KClass, `false` otherwise.
     */
    fun hasClassOrInterfaceDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified interface or object declaration.
     *
     * @param predicate The predicate function used to determine if interface or object declaration satisfies a condition.
     * @return `true` if the declaration has the specified interface or object declaration (or any interface or object
     * declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasInterfaceOrObjectDeclaration(predicate: ((KoInterfaceAndObjectDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has an interface or an object declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the interface or object declaration to check for.
     * @return `true` if the declaration has an interface or an object declaration matching the specified KClass, `false` otherwise.
     */
    fun hasInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified class, interface or object declaration.
     *
     * @param predicate The predicate function used to determine if class, interface or object declaration satisfies a condition.
     * @return `true` if the declaration has the specified class, interface or object declaration (or any class,
     * interface or object declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasClassOrInterfaceOrObjectDeclaration(predicate: ((KoClassAndInterfaceAndObjectDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a class, an interface or an object declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the class, interface or object declaration to check for.
     * @return `true` if the declaration has a class, an interface  or an object declaration matching the specified
     * KClass, `false` otherwise.
     */
    fun hasClassOrInterfaceOrObjectDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified type alias declaration.
     *
     * @param predicate The predicate function used to determine if a type alias declaration satisfies a condition.
     * @return `true` if the declaration has the specified type alias declaration (or any type alias declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasTypeAliasDeclaration(predicate: ((KoTypeAliasDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a specified import alias declaration.
     *
     * @param predicate The predicate function used to determine if an import alias declaration satisfies a condition.
     * @return `true` if the declaration has the specified import alias declaration (or any import alias declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasImportAliasDeclaration(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a specified kotlin type declaration.
     *
     * @param predicate The predicate function used to determine if a kotlin type declaration satisfies a condition.
     * @return `true` if the declaration has the specified kotlin type declaration (or any kotlin type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasKotlinTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a kotlin type declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the kotlin type declaration to check for.
     * @return `true` if the declaration has a kotlin type declaration matching the specified KClass, `false` otherwise.
     */
    fun hasKotlinTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified kotlin basic type declaration.
     *
     * @param predicate The predicate function used to determine if a kotlin basic type declaration satisfies a condition.
     * @return `true` if the declaration has the specified kotlin basic type declaration (or any kotlin basic type
     * declaration if [predicate] is `null`), `false` otherwise.
     */
    fun hasKotlinBasicTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a kotlin basic type declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the kotlin basic type declaration to check for.
     * @return `true` if the declaration has a kotlin basic type declaration matching the specified KClass, `false` otherwise.
     */
    fun hasKotlinBasicTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified kotlin collection type declaration.
     *
     * @param predicate The predicate function used to determine if a kotlin collection type declaration satisfies a condition.
     * @return `true` if the declaration has the specified kotlin collection type declaration (or any kotlin collection type
     * declaration if [predicate] is `null`), `false` otherwise.
     */
    fun hasKotlinCollectionTypeDeclaration(predicate: ((KoKotlinTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a kotlin collection type declaration of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the kotlin collection type declaration to check for.
     * @return `true` if the declaration has a kotlin collection type declaration matching the specified KClass, `false` otherwise.
     */
    fun hasKotlinCollectionTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified type parameter declaration.
     *
     * @param predicate The predicate generic used to determine if a type parameter declaration satisfies a condition.
     * @return `true` if the declaration has the specified type parameter declaration (or any parameter type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasTypeParameterDeclaration(predicate: ((KoTypeParameterDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a specified external type declaration.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param predicate The predicate external used to determine if a external type declaration satisfies a condition.
     * @return `true` if the declaration has the specified external type declaration (or any external type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    fun hasExternalDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a external type declaration of the specified Kotlin class.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param kClass The Kotlin class representing the external type declaration to check for.
     * @return `true` if the declaration has a external type declaration (type defined outside the project codebase for e.g.
     * in external library) matching the specified KClass, `false` otherwise.
     */
    fun hasExternalDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether type has a specified external type declaration.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param predicate The predicate external used to determine if a external type declaration satisfies a condition.
     * @return `true` if the type has the specified external type declaration (or any external type declaration if [predicate] is
     * `null`), `false` otherwise.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("hasExternalDeclaration"))
    fun hasExternalTypeDeclaration(predicate: ((KoExternalDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether type has a external type declaration of the specified Kotlin class.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     *
     * @param kClass The Kotlin class representing the external type declaration to check for.
     * @return `true` if the type has a external type declaration (type defined outside the project codebase for e.g.
     * in external library) matching the specified KClass, `false` otherwise.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("hasExternalDeclarationOf"))
    fun hasExternalTypeDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified function declaration.
     *
     * @param predicate The predicate function used to determine if a function declaration satisfies a condition.
     * @return `true` if the declaration has the specified function declaration (or any function declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasFunctionDeclaration(predicate: ((KoFunctionDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a function declaration of the specified Kotlin function.
     *
     * @param kClass The Kotlin class representing the function declaration to check for.
     * @return `true` if the declaration has a function declaration matching the specified KClass, `false` otherwise.
     */
    fun hasFunctionDeclarationOf(kClass: KClass<*>): Boolean

    /**
     * Whether declaration has a specified property declaration.
     *
     * @param predicate The predicate function used to determine if a property declaration satisfies a condition.
     * @return `true` if the declaration has the specified property declaration (or any property declaration if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasPropertyDeclaration(predicate: ((KoPropertyDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a property declaration of the specified Kotlin property.
     *
     * @param kClass The Kotlin class representing the property declaration to check for.
     * @return `true` if the declaration has a property declaration matching the specified KClass, `false` otherwise.
     */
    fun hasPropertyDeclarationOf(kClass: KClass<*>): Boolean
}
