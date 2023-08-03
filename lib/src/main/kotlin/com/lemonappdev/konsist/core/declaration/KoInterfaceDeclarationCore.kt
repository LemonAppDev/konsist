package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoParentInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoFunModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoSealedModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoInterfaceDeclarationCore private constructor(
    override val ktClass: KtClass,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoInterfaceDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoClassProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDeclarationProviderCore,
    KoFunctionProviderCore,
    KoInterfaceProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoObjectProviderCore,
    KoPackageDeclarationProviderCore,
    KoParentInterfaceProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoPropertyProviderCore,
    KoRepresentsTypeProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore,
    KoTopLevelProviderCore,
    KoVisibilityModifierProviderCore,
    KoActualModifierProviderCore,
    KoExpectModifierProviderCore,
    KoFunModifierProviderCore,
    KoSealedModifierProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktClass }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktClass }

    override val psiElement: PsiElement by lazy { ktClass }

    override val ktElement: KtElement by lazy { ktClass }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil
        .getKoDeclarations(ktClass, includeNested, includeLocal, this)

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoInterfaceDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, containingDeclaration: KoContainingDeclarationProvider): KoInterfaceDeclaration =
            cache.getOrCreateInstance(ktClass, containingDeclaration) {
                KoInterfaceDeclarationCore(ktClass, containingDeclaration)
            }
    }
}
