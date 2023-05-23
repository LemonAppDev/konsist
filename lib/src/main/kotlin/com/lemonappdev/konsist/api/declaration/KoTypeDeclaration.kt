package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin declaration.
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
     * Returns `true` if this type is import alias.
     *
     * @return `true` if this type is import alias, `false` otherwise.
     */
    fun isImportAlias(): Boolean
}
