package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentInterfaceDeclarationCore private constructor(override val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParentInterfaceDeclaration,
    KoParentDeclarationCore,
    KoDelegateProviderCore {
    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

    override val delegateName: String?
        get() = if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry) {
            ktSuperTypeListEntry
                .delegateExpression
                ?.text
        } else {
            null
        }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoParentInterfaceDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoParentInterfaceDeclaration =
            cache.getOrCreateInstance(
                ktSuperTypeListEntry,
                containingDeclaration,
            ) { KoParentInterfaceDeclarationCore(ktSuperTypeListEntry) }
    }
}
