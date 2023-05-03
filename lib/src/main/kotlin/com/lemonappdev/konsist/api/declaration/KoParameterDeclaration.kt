package com.lemonappdev.konsist.api.declaration

interface KoParameterDeclaration : KoDeclaration {
    val type: KoTypeDeclaration

    val defaultValue: String?

    fun hasVarargModifier(): Boolean

    fun hasNoInlineModifier(): Boolean

    fun hasCrossInlineModifier(): Boolean

    fun hasDefaultValue(value: String? = null): Boolean

    fun representsType(type: String): Boolean
}
