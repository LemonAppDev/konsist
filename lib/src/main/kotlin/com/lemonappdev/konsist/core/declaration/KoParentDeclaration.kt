package com.lemonappdev.konsist.core.declaration

interface KoParentDeclaration : KoNamedDeclaration {
    val delegateName: String?

    fun hasDelegate(delegateName: String? = null): Boolean
}
