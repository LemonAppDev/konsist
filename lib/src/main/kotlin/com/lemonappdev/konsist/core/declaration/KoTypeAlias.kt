package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.psiUtil.hasActualModifier

class KoTypeAlias private constructor(private val ktTypeAlias: KtTypeAlias) : KoDeclaration(ktTypeAlias) {

    val type by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoType.getInstance(it) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclaration = this)
    }

    fun hasActualModifier() = ktTypeAlias.hasActualModifier()

    companion object {
        private val cache = KoDeclarationCache<KoTypeAlias>()

        fun getInstance(ktTypeAlias: KtTypeAlias) =
            cache.getOrCreateInstance(ktTypeAlias) { KoTypeAlias(ktTypeAlias) }
    }
}
