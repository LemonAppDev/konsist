package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParentDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoTypeAliasDeclarationImpl private constructor(
    private val ktTypeAlias: KtTypeAlias,
    override val parentDeclaration: KoParentDeclarationProvider?,
) :
    KoTypeAliasDeclaration,
    KoContainingFileProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoParentDeclarationProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoTypeProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktTypeAlias }

    override val ktFile: KtFile? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktTypeAlias }

    override val koFiles: Sequence<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktTypeAlias }

    override val ktElement: KtElement by lazy { ktTypeAlias }

    override val type: KoTypeDeclaration by lazy {
        ktTypeAlias
            .getTypeReference()
            ?.let { KoTypeDeclarationImpl.getInstance(it, this) }
            ?: throw KoInternalException("Type alias has no type", koBaseProvider = this)
    }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeAliasDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeAlias: KtTypeAlias, parentDeclaration: KoParentDeclarationProvider?): KoTypeAliasDeclaration =
            cache.getOrCreateInstance(ktTypeAlias, parentDeclaration) { KoTypeAliasDeclarationImpl(ktTypeAlias, parentDeclaration) }
    }
}
