package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoDelegateProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentDeclarationImpl private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoParentDeclaration,
    KoBaseProviderCore,
    KoDelegateProviderCore,
    KoNameProviderCore {
    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

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
            parent: KoParentProvider?,
        ): KoParentDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, parent) { KoParentDeclarationImpl(ktSuperTypeListEntry) }
    }
}
