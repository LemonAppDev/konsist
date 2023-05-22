package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoParentDeclarationImpl private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) :
    KoNamedDeclarationImpl(ktSuperTypeListEntry), KoParentDeclaration {

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

    override fun hasDelegate(delegateName: String?): Boolean = when (delegateName) {
        null -> this.delegateName != null
        else -> name == delegateName
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktSuperTypeListEntry: KtSuperTypeListEntry, parent: KoBaseDeclaration): KoParentDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, parent) { KoParentDeclarationImpl(ktSuperTypeListEntry) }
    }
}
