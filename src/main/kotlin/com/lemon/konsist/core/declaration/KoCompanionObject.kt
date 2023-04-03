package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoCompanionObject(private val ktObjectDeclaration: KtObjectDeclaration) : KoComplexDeclaration(ktObjectDeclaration) {
    fun hasExplicitName() = name != DEFAULT_COMPANION_OBJECT_NAME

    companion object {
        const val DEFAULT_COMPANION_OBJECT_NAME = "Companion"
    }
}
