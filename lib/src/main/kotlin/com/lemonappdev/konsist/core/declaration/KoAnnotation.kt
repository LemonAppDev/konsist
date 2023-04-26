package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtAnnotationEntry

class KoAnnotation private constructor(
    private val ktAnnotationEntry: KtAnnotationEntry,
) : KoNamedDeclaration(ktAnnotationEntry) {
    override val name by lazy { ktAnnotationEntry.shortName.toString() }

    override val text: String by lazy { ktAnnotationEntry.text.removePrefix("@") }

    val fullyQualifiedName: String by lazy {
        containingFile
            .imports
            .firstOrNull { it.text.endsWith(".$name") }
            ?.name ?: name
    }

    fun representsType(name: String) =
        name == this.name || name == fullyQualifiedName

    inline fun <reified T>representsTypeOf() = T::class.qualifiedName == fullyQualifiedName

    companion object {
        private val cache = KoDeclarationCache<KoAnnotation>()

        fun getInstance(ktObjectDeclaration: KtAnnotationEntry) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoAnnotation(ktObjectDeclaration) }
    }
}
