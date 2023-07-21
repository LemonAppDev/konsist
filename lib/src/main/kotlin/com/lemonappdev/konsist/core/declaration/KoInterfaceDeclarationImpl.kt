package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoInterfaceDeclarationImpl private constructor(
    private val ktClass: KtClass,
    override val parent: KoParentProvider?,
) :
    KoInterfaceDeclaration,
    KoContainingFileProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoBaseProviderCore,
    KoDeclarationProviderCore,
    KoClassProviderCore,
    KoInterfaceProviderCore,
    KoObjectProviderCore,
    KoPropertyProviderCore,
    KoFunctionProviderCore,
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoRepresentsTypeProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktClass }

    override val ktFile: KtFile? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktClass }

    override val koFiles: Sequence<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktClass }

    override val ktElement: KtElement by lazy { ktClass }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseProvider> = KoDeclarationProviderCoreUtil
        .getKoDeclarations(ktClass, includeNested, includeLocal, this)

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoInterfaceDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, parent: KoParentProvider?,): KoInterfaceDeclaration =
            cache.getOrCreateInstance(ktClass, parent) {
                KoInterfaceDeclarationImpl(ktClass, parent)
            }
    }
}
