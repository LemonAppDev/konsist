package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtAnnotationEntry

open class KoAnnotation private constructor(
    private val ktObjectDeclaration: KtAnnotationEntry,
) : KoNamedDeclaration(ktObjectDeclaration) {
    val type = ktObjectDeclaration.typeReference?.text ?: ""

    override val name by lazy { ktObjectDeclaration.shortName.toString() }

    companion object {
        private val cache = KoDeclarationCache<KoAnnotation>()

        fun getInstance(ktObjectDeclaration: KtAnnotationEntry) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoAnnotation(ktObjectDeclaration) }
    }
}
