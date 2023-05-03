package com.lemonappdev.konsist.core.declaration

interface KoTypeAliasDeclaration {
    val type: KoTypeDeclaration

    fun hasActualModifier(): Boolean
}
