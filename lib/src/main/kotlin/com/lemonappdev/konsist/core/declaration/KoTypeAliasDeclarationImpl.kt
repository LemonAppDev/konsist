package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoTypeAliasDeclarationImpl private constructor(
    private val ktTypeAlias: KtTypeAlias,
    override val parentDeclaration: KoParentProvider?,
) :
    KoTypeAliasDeclaration,
    KoBaseDeclarationImpl(ktTypeAlias),
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoTypeProviderCore {
    override val ktAnnotated: KtAnnotated
        get() = ktTypeAlias

    override val ktFile: KtFile?
        get() = null

    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktTypeAlias

    override val type: KoTypeDeclaration by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclarationImpl.getInstance(it, this) }
            ?: throw KoInternalException("Type alias has no type", koBaseDeclaration = this)
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeAliasDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeAlias: KtTypeAlias, parentDeclaration: KoParentProvider?): KoTypeAliasDeclaration =
            cache.getOrCreateInstance(ktTypeAlias, parentDeclaration) { KoTypeAliasDeclarationImpl(ktTypeAlias, parentDeclaration) }
    }
}
