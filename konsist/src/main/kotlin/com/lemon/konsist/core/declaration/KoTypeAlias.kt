package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtTypeAlias

class KoTypeAlias private constructor(private val ktTypeAlias: KtTypeAlias) : KoDeclaration(ktTypeAlias) {
    val type by lazy { ktTypeAlias.getTypeReference()?.typeElement?.text }

    companion object {
        private val cache = KoDeclarationCache<KoTypeAlias>()

        fun getInstance(ktTypeAlias: KtTypeAlias) =
            cache.getOrCreateInstance(ktTypeAlias) { KoTypeAlias(ktTypeAlias) }
    }
}
