package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeAlias

class KoTypeAliasDeclarationImpl private constructor(private val ktTypeAlias: KtTypeAlias) : KoDeclarationImpl(ktTypeAlias) {

    val type by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclarationImpl.getInstance(it) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclarationImpl = this)
    }

    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    companion object {
        private val cache = KoDeclarationCache<KoTypeAliasDeclarationImpl>()

        fun getInstance(ktTypeAlias: KtTypeAlias) =
            cache.getOrCreateInstance(ktTypeAlias) { KoTypeAliasDeclarationImpl(ktTypeAlias) }
    }
}
