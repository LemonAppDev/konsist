package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

internal class KoAnnotationDeclarationCore private constructor(
    private val ktAnnotationEntry: KtAnnotationEntry,
) : KoAnnotationDeclaration,
    KoBaseProviderCore,
    KoArgumentProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoRepresentsTypeProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktAnnotationEntry }

    override val ktElement: KtElement by lazy { ktAnnotationEntry }

    override val name: String by lazy { ktAnnotationEntry.shortName.toString() }

    override val arguments: List<KoArgumentDeclaration> by lazy {
        ktAnnotationEntry
            .children
            .filterIsInstance<KtValueArgumentList>()
            .firstOrNull()
            ?.children
            ?.filterIsInstance<KtValueArgument>()
            ?.map { KoArgumentDeclarationCore.getInstance(it, this) } ?: emptyList()
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoAnnotationDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktObjectDeclaration: KtAnnotationEntry,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoAnnotationDeclaration =
            cache.getOrCreateInstance(ktObjectDeclaration, containingDeclaration) { KoAnnotationDeclarationCore(ktObjectDeclaration) }
    }
}
