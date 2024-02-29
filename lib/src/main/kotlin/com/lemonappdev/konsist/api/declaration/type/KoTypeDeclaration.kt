package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin type declaration.
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
    KoBaseDeclaration, // Todo: add tests
    KoBaseProvider, // Todo: add tests
    KoFullyQualifiedNameProvider, // Todo: add tests
    KoNameProvider, // Todo: add tests
    KoTextProvider, // Todo: add tests
    KoPackageProvider, // Todo: add tests
    KoResideInPackageProvider, // Todo: add tests
    KoKotlinTypeProvider,
//    KoGenericTypeProvider, // Todo: add tests
    KoNullableProvider
