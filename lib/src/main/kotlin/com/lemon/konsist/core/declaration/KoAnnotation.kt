package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtAnnotationEntry

open class KoAnnotation private constructor(
    private val ktAnnotationEntry: KtAnnotationEntry,
) : KoNamedDeclaration(ktAnnotationEntry) {
    val type = ktAnnotationEntry.typeReference?.text ?: ""

    override val name by lazy { ktAnnotationEntry.shortName.toString() }

    val fullyQualifiedName: String by lazy {
        containingFile
            .imports
            .firstOrNull { it.text.endsWith(".$type") }
            ?.name ?: name
    }

    companion object {
        private val cache = KoDeclarationCache<KoAnnotation>()

        fun getInstance(ktObjectDeclaration: KtAnnotationEntry) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoAnnotation(ktObjectDeclaration) }
    }
}
