package com.lemonappdev.konsist.api.declaration

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
 *  importAliasName // ""
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
 * sourceType // "String"
 * name // "ImportAlias?"
 * isNullable // true
 * ```
 */
interface KoTypeDeclaration :
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * The import alias name.
     */
    val importAliasName: String

    /**
     * The source type.
     */
    val sourceType: String

    /**
     * The fully qualified name of the type.
     */
    val fullyQualifiedName: String

    /**
     * Whatever type is nullable.
     */
    val isNullable: Boolean

    /**
     * Returns `true` if this type is import alias.
     *
     * @return `true` if this type is import type alias, `false` otherwise.
     */
    fun isImportAlias(): Boolean
}
