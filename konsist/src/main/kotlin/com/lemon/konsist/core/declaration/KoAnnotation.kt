package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtFile

open class KoAnnotation private constructor(
    private val ktObjectDeclaration: KtAnnotationEntry,
) : KoNamedDeclaration(ktObjectDeclaration) {
    val type = ktObjectDeclaration.typeReference?.text ?: ""

    override val name by lazy { ktObjectDeclaration.shortName.toString() }

    fun getFullyQualifiedClassName(className: String, ktFile: KtFile) =
        ktFile
            .importDirectives
            .firstOrNull { it.importedName?.identifier == className }
            ?.importedFqName
            ?.toString()
            ?: className

    companion object {
        private val cache = KoDeclarationCache<KoAnnotation>()

        fun getInstance(ktObjectDeclaration: KtAnnotationEntry) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoAnnotation(ktObjectDeclaration) }
    }
}
