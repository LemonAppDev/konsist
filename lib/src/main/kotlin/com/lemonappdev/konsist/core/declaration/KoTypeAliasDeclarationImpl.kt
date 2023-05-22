package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeAlias

internal class KoTypeAliasDeclarationImpl private constructor(private val ktTypeAlias: KtTypeAlias, parent: KoBaseDeclaration?) :
    KoDeclarationImpl(ktTypeAlias, parent),
    KoTypeAliasDeclaration {

    override val type: KoTypeDeclaration by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclarationImpl.getInstance(it, this) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclarationImpl = this)
    }

    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    internal companion object {
        private val cache = KoDeclarationCache<KoTypeAliasDeclarationImpl>()

        internal fun getInstance(ktTypeAlias: KtTypeAlias, parent: KoBaseDeclaration): KoTypeAliasDeclaration =
            cache.getOrCreateInstance(ktTypeAlias, parent) { KoTypeAliasDeclarationImpl(ktTypeAlias, parent) }
    }
}
