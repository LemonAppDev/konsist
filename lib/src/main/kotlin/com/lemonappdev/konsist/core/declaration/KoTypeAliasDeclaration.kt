package com.lemonappdev.konsist.core.declaration

interface KoTypeAliasDeclaration : KoDeclaration {
    val type: KoTypeDeclaration

    fun hasActualModifier(): Boolean
}
