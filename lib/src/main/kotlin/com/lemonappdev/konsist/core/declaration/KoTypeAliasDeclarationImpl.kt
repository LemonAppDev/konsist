package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeAlias

internal class KoTypeAliasDeclarationImpl private constructor(private val ktTypeAlias: KtTypeAlias, parentDeclaration: KoBaseDeclaration?) :
    KoDeclarationImpl(ktTypeAlias, parentDeclaration),
    KoTypeAliasDeclaration {

    override val type: KoTypeDeclaration by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclarationImpl.getInstance(it, this) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclaration = this)
    }

    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeAliasDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeAlias: KtTypeAlias, parentDeclaration: KoBaseDeclaration?): KoTypeAliasDeclaration =
            cache.getOrCreateInstance(ktTypeAlias, parentDeclaration) { KoTypeAliasDeclarationImpl(ktTypeAlias, parentDeclaration) }
    }
}
