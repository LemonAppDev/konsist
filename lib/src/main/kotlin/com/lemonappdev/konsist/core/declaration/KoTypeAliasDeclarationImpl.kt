package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeAlias

internal class KoTypeAliasDeclarationImpl private constructor(private val ktTypeAlias: KtTypeAlias) :
    KoDeclarationImpl(ktTypeAlias),
    KoTypeAliasDeclaration {

    override val type by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclarationImpl.getInstance(it) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclarationImpl = this)
    }

    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    internal companion object {
        private val cache = KoDeclarationCache<KoTypeAliasDeclarationImpl>()

        internal fun getInstance(ktTypeAlias: KtTypeAlias) =
            cache.getOrCreateInstance(ktTypeAlias) { KoTypeAliasDeclarationImpl(ktTypeAlias) }
    }
}
