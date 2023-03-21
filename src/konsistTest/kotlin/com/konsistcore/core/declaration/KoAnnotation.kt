package com.konsistcore.core.declaration

import org.jetbrains.kotlin.psi.KtAnnotationEntry

open class KoAnnotation(
    private val ktObjectDeclaration: KtAnnotationEntry,
) : KoNamedDeclaration(ktObjectDeclaration) {
    val type = ktObjectDeclaration.typeReference?.text ?: ""
}
