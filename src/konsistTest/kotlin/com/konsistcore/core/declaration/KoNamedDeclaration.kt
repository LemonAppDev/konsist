package com.konsistcore.core.declaration

import org.jetbrains.kotlin.psi.KtElement

abstract class KoNamedDeclaration(private val ktElement: KtElement) : KoBaseDeclaration(ktElement) {
    val name by lazy { ktElement.name ?: "" }
}
