package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement

internal class KoAnnotationDeclarationImpl private constructor(
    private val ktAnnotationEntry: KtAnnotationEntry,
) : KoAnnotationDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPathProviderCore,
    KoRepresentsTypeProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktAnnotationEntry }

    override val ktElement: KtElement by lazy { ktAnnotationEntry }

    override val name: String by lazy { ktAnnotationEntry.shortName.toString() }

    override val fullyQualifiedName: String by lazy {
        containingFile
            .imports
            .firstOrNull { it.text.endsWith(".$name") }
            ?.name ?: ""
    }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoAnnotationDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktObjectDeclaration: KtAnnotationEntry,
            parent: KoContainingDeclarationProvider?,
        ): KoAnnotationDeclaration =
            cache.getOrCreateInstance(ktObjectDeclaration, parent) { KoAnnotationDeclarationImpl(ktObjectDeclaration) }
    }
}
