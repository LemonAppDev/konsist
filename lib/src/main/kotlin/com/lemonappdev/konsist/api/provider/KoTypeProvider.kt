package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a kotlin type.
 */
interface KoTypeProvider : KoBaseProvider {
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
     * Determines whatever source declaration is a function.
     */
    val isFunctionType: Boolean

    /**
     * Determines whatever source declaration is an external type.
     * An external type refers to a type that is defined outside the project's codebase. for e.g. in external library.
     */
    val isExternalType: Boolean
}
