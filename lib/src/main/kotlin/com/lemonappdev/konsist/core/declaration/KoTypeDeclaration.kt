package com.lemonappdev.konsist.core.declaration

interface KoTypeDeclaration : KoNamedDeclaration {
    val importAliasName: String

    val sourceType: String

    val fullyQualifiedName: String

    fun isImportAlias(): Boolean
}
