package com.lemonappdev.konsist.core.declaration

interface KoPropertyDeclaration : KoDeclaration {
    val isVar: Boolean

    val isVal: Boolean

    val delegateName: String?

    val type: KoTypeDeclaration?

    fun hasLateinitModifier(): Boolean

    fun hasOverrideModifier(): Boolean

    fun hasAbstractModifier(): Boolean

    fun hasOpenModifier(): Boolean

    fun hasFinalModifier(): Boolean

    fun hasActualModifier(): Boolean

    fun hasExpectModifier(): Boolean

    fun hasConstModifier(): Boolean

    fun isExtension(): Boolean

    fun hasDelegate(name: String? = null): Boolean

    fun hasType(type: String? = null): Boolean
}
