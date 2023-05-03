package com.lemonappdev.konsist.api.declaration

interface KoObjectDeclaration : KoComplexDeclaration {
    fun hasDataModifier(): Boolean
}
