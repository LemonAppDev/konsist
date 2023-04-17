package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

class KoParent private constructor(private val ktSuperTypeListEntry: KtSuperTypeListEntry): KoNamedDeclaration(ktSuperTypeListEntry) {

    override val name: String by lazy {
        ktSuperTypeListEntry
            .text
            .removeSuffix("()")
            .substringBefore(" by")
    }

    val delegateName by lazy {
        if (ktSuperTypeListEntry is KtDelegatedSuperTypeEntry){
            ktSuperTypeListEntry.text
        } else null
    }

    fun hasDelegate() = delegateName != null

    fun hasDelegate(delegateName: String) = name == delegateName

    companion object {
        private val cache = KoDeclarationCache<KoParent>()

        fun getInstance(ktSuperTypeListEntry: KtSuperTypeListEntry) =
            cache.getOrCreateInstance(ktSuperTypeListEntry) { KoParent(ktSuperTypeListEntry) }
    }
}