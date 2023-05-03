package com.lemonappdev.konsist.api.declaration

interface KoInterfaceDeclaration : KoComplexDeclaration {
    fun hasActualModifier(): Boolean

    fun hasExpectModifier(): Boolean
}
