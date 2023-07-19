package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin declaration.
 *
 *  Example 1
 *
 *  This code snippet...
 *  ```kotlin
 *  val sampleProperty: String
 *  ```
 *
 *  ...will be represented as:
 *
 *  ```kotlin
 *  importAliasName // null
 *  sourceType // "String"
 *  name // "String"
 *  isNullable // false
 *  ```
 *
 * Example 2
 * This code snippet...
 * ```kotlin
 * import com.SampleType as ImportAlias
 * val sampleProperty: ImportAlias?
 * ```
 *
 * ...will be represented as:
 *
 * ```kotlin
 * importAliasName // "ImportAlias"
 * sourceType // "SampleType"
 * name // "ImportAlias?"
 * isNullable // true
 * ```
 */
interface KoTypeDeclaration :
    KoBaseDeclaration,
    KoFullyQualifiedNameProvider,
    KoParentProvider,
    KoNullableProvider {
    /**
     * The import alias name.
     */
    val aliasType: String?

    /**
     * The source type.
     */
    val sourceType: String

    /**
     * Whatever type is a build in Kotlin type. It can be a basic Kotlin type [Basic types](https://kotlinlang.org/docs/basic-types.html)
     * or collection type [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
     */
    val isKotlinType: Boolean

    /**
     * Whatever type is generic type.
     */
    val isGenericType: Boolean

    /**
     * Returns `true` if this type is import alias.
     *
     * @return `true` if this type is import type alias, `false` otherwise.
     */
    fun isAlias(): Boolean
}
