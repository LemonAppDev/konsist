package com.lemonappdev.konsist.api.declaration

interface KoParentDeclaration : KoNamedDeclaration {
    val delegateName: String?

    fun hasDelegate(delegateName: String? = null): Boolean
}
