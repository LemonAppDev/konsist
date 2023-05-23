package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin declaration.
 *
 * E.g.
 * * val sampleProperty: String
 *
 * [importAliasName] == ""
 *
 * [sourceType] == "String"
 *
 * [name] == "String"
 *
 * [isNullable] == false
 *
 * * import com.SampleType as ImportAlias
 *
 * val sampleProperty: ImportAlias?
 *
 * [importAliasName] == "ImportAlias"
 *
 * [sourceType] == "String"
 *
 * [name] == "ImportAlias?"
 *
 * [isNullable] == true
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
