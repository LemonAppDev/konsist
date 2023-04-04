package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement

abstract class KoNamedDeclaration(private val ktElement: KtElement) : KoBaseDeclaration(ktElement) {
    open val name by lazy { ktElement.name ?: "" }
}
