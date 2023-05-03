package com.lemonappdev.konsist.api.declaration

interface KoTypeDeclaration : KoNamedDeclaration {
    val importAliasName: String

    val sourceType: String

    val fullyQualifiedName: String

    fun isImportAlias(): Boolean
}
