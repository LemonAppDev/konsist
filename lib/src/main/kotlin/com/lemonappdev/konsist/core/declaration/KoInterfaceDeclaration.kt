package com.lemonappdev.konsist.core.declaration

interface KoInterfaceDeclaration : KoComplexDeclaration {
    fun hasActualModifier(): Boolean

    fun hasExpectModifier(): Boolean
}
