package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a kotlin type.
 */
interface KoKotlinTypeProvider : KoBaseProvider {
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
}
