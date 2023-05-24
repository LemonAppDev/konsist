package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin declaration.
 *
 * E.g.
 * 1. For this code snippet:
 *      ```kotlin
 *      val sampleProperty: String
 *      val type = sampleProperty.type
 *
 *      type.importAliasName // ""
 *      type.sourceType // "String"
 *      type.name // "String"
 *      type.isNullable // false
 *      ```
 *
 * 2. For this code snippet:
 *      ```kotlin
 *      import com.SampleType as ImportAlias
 *      val sampleProperty: ImportAlias?
 *      val type = sampleProperty.type
 *
 *      type.importAliasName // "ImportAlias"
 *      type.sourceType // "String"
 *      type.name // "ImportAlias?"
 *      type.isNullable // true
 *      ```
 */
interface KoTypeDeclaration : KoNamedDeclaration {
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
     */
    fun isImportAlias(): Boolean
}
