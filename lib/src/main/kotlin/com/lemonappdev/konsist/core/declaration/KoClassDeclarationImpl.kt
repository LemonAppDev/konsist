package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFinalModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOpenModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSealedModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVisibilityModifierProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoHasTestProviderCore
import com.lemonappdev.konsist.core.provider.KoInitBlockProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParentClassProviderCore
import com.lemonappdev.konsist.core.provider.KoParentDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoParentInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPrimaryConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSecondaryConstructorsProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoAbstractModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoAnnotationModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoDataModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoEnumModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoFinalModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoInnerModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoOpenModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoSealedModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoValueModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoClassDeclarationImpl private constructor(
    override val ktClass: KtClass,
    override val parent: KoParentProvider?,
) : KoClassDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoClassProviderCore,
    KoConstructorProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDeclarationProviderCore,
    KoFunctionProviderCore,
    KoHasTestProviderCore,
    KoInitBlockProviderCore,
    KoInterfaceProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoObjectProviderCore,
    KoPackageProviderCore,
    KoParentClassProviderCore,
    KoParentDeclarationProviderCore,
    KoParentInterfaceProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoPrimaryConstructorProviderCore,
    KoPropertyProviderCore,
    KoRepresentsTypeProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoTextProviderCore,
    KoTopLevelProviderCore,
    KoVisibilityModifierProviderCore,
    KoEnumModifierProviderCore,
    KoSealedModifierProviderCore,
    KoInnerModifierProviderCore,
    KoValueModifierProviderCore,
    KoAnnotationModifierProviderCore,
    KoDataModifierProviderCore,
    KoActualModifierProviderCore,
    KoExpectModifierProviderCore,
    KoAbstractModifierProviderCore,
    KoOpenModifierProviderCore,
    KoFinalModifierProviderCore {
    override val ktFile: KtFile? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktClass }

    override val ktAnnotated: KtAnnotated by lazy { ktClass }

    override val koFiles: List<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktClass }

    override val ktElement: KtElement by lazy { ktClass }

    override val ktClassOrObject: KtClassOrObject by lazy { ktClass }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil
        .getKoDeclarations(ktClass, includeNested, includeLocal, this)

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoClassDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, parent: KoParentProvider?): KoClassDeclaration =
            cache.getOrCreateInstance(ktClass, parent) {
                KoClassDeclarationImpl(ktClass, parent)
            }
    }
}
