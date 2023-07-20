package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoParent
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoParentDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentImpl private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParent,
    KoContainingFileProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoParentDeclarationProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoBaseProviderCore,
    KoDelegateProviderCore {
    override val psiElement: PsiElement by lazy { ktSuperTypeListEntry }

    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

    override val parentDeclaration: KoParentDeclarationProvider? by lazy { this }

    override val name: String by lazy {
        ktSuperTypeListEntry
            .text
            .removeSuffix("()")
            .replace("\n", " ")
            .substringBefore(" by")
    }

    override val delegateName: String? by lazy {
        if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry) {
            ktSuperTypeListEntry
                .delegateExpression
                ?.text
        } else {
            null
        }
    }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoParent> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            parentDeclaration: KoParentDeclarationProvider?,
        ): KoParent =
            cache.getOrCreateInstance(ktSuperTypeListEntry, parentDeclaration) { KoParentImpl(ktSuperTypeListEntry) }
    }
}
