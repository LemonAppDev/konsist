package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider

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
    KoParentDeclarationProvider,
    KoNullableProvider,
    KoKotlinTypeProvider,
    KoSourceAndAliasTypeProvider,
    KoGenericTypeProvider
