package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

class KoParent private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry) : KoNamedDeclaration(ktSuperTypeListEntry) {

    override val name: String by lazy {
        ktSuperTypeListEntry
            .text
            .removeSuffix("()")
            .replace("\n", " ")
            .substringBefore(" by")
    }

    val delegateName by lazy {
        if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry) {
            ktSuperTypeListEntry
                .delegateExpression
                ?.text
        } else {
            null
        }
    }

    fun hasDelegate(delegateName: String? = null) = when (delegateName) {
        null -> this.delegateName != null
        else -> name == delegateName
    }

    companion object {
        private val cache = KoDeclarationCache<KoParent>()

        fun getInstance(ktSuperTypeListEntry: KtSuperTypeListEntry) =
            cache.getOrCreateInstance(ktSuperTypeListEntry) { KoParent(ktSuperTypeListEntry) }
    }
}
