package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentClassDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentClassDeclarationImpl private constructor(override val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParentClassDeclaration,
    KoParentDeclarationImpl {
    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoParentClassDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoParentClassDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, containingDeclaration) { KoParentClassDeclarationImpl(ktSuperTypeListEntry) }
    }
}
