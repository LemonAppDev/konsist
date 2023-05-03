package com.lemonappdev.konsist.api.declaration

interface KoTypeAliasDeclaration : KoDeclaration {
    val type: KoTypeDeclaration

    fun hasActualModifier(): Boolean
}
