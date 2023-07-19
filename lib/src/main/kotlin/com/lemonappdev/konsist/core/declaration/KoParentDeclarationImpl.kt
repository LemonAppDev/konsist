package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentDeclarationImpl private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoBaseDeclarationImpl(ktSuperTypeListEntry),
    KoParentDeclaration,
    KoDelegateProviderCore {

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

    internal companion object {
        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            parentDeclaration: KoParentDeclarationProvider?,
        ): KoParentDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, parentDeclaration) { KoParentDeclarationImpl(ktSuperTypeListEntry) }
    }
}
