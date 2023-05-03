package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeAlias

class KoTypeAliasDeclaration private constructor(private val ktTypeAlias: KtTypeAlias) : KoDeclaration(ktTypeAlias) {

    val type by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclaration.getInstance(it) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclaration = this)
    }

    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    companion object {
        private val cache = KoDeclarationCache<KoTypeAliasDeclaration>()

        fun getInstance(ktTypeAlias: KtTypeAlias) =
            cache.getOrCreateInstance(ktTypeAlias) { KoTypeAliasDeclaration(ktTypeAlias) }
    }
}
