package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration

interface KoObjectDeclaration : KoComplexDeclaration {
    fun hasDataModifier(): Boolean
}
