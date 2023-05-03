package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration

interface KoInterfaceDeclaration : KoComplexDeclaration {
    fun hasActualModifier(): Boolean

    fun hasExpectModifier(): Boolean
}
