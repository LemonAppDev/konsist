package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPrimaryConstructor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoPrimaryConstructorDeclarationImpl private constructor(
    private val ktPrimaryConstructor: KtPrimaryConstructor,
    override val parent: KoParentProvider?,
) :
    KoPrimaryConstructorDeclaration,
    KoConstructorProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoParametersProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktPrimaryConstructor }

    override val ktFile: KtFile? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktPrimaryConstructor }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktPrimaryConstructor }

    override val koFiles: Sequence<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktPrimaryConstructor }

    override val ktElement: KtElement by lazy { ktPrimaryConstructor }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPrimaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPrimaryConstructor: KtPrimaryConstructor,
            parent: KoParentProvider?,
        ): KoPrimaryConstructorDeclaration =
            cache.getOrCreateInstance(ktPrimaryConstructor, parent) {
                KoPrimaryConstructorDeclarationImpl(
                    ktPrimaryConstructor,
                    parent,
                )
            }
    }
}
