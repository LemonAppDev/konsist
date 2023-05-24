package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtAnnotationEntry

internal class KoAnnotationDeclarationImpl private constructor(
    private val ktAnnotationEntry: KtAnnotationEntry,
) : KoNamedDeclarationImpl(ktAnnotationEntry), KoAnnotationDeclaration {
    override val name by lazy { ktAnnotationEntry.shortName.toString() }

    override val fullyQualifiedName: String by lazy {
        containingFile
            .imports
            .firstOrNull { it.text.endsWith(".$name") }
            ?.name ?: name
    }

    override fun representsType(name: String) = name == this.name || name == fullyQualifiedName

    internal companion object {
        private val cache = KoDeclarationCache<KoAnnotationDeclarationImpl>()

        internal fun getInstance(ktObjectDeclaration: KtAnnotationEntry, parent: KoParent) =
            cache.getOrCreateInstance(ktObjectDeclaration, parent) { KoAnnotationDeclarationImpl(ktObjectDeclaration) }
    }
}
