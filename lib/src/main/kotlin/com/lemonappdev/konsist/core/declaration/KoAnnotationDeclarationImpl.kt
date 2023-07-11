package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import org.jetbrains.kotlin.psi.KtAnnotationEntry

internal class KoAnnotationDeclarationImpl private constructor(
    private val ktAnnotationEntry: KtAnnotationEntry,
) : KoBaseDeclarationImpl(ktAnnotationEntry),
    KoAnnotationDeclaration,
    KoRepresentsTypeProviderCore {

    override val name: String by lazy { ktAnnotationEntry.shortName.toString() }

    override val fullyQualifiedName: String by lazy {
        containingFile
            .imports
            .firstOrNull { it.text.endsWith(".$name") }
            ?.name ?: ""
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoAnnotationDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktObjectDeclaration: KtAnnotationEntry, parentDeclaration: KoParentProvider?): KoAnnotationDeclaration =
            cache.getOrCreateInstance(ktObjectDeclaration, parentDeclaration) { KoAnnotationDeclarationImpl(ktObjectDeclaration) }
    }
}
