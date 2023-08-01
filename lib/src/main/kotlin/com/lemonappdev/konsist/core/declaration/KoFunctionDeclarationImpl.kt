package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoExplicitReturnTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoImplementationProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

@Suppress("detekt.TooManyFunctions")
internal class KoFunctionDeclarationImpl private constructor(
    override val ktFunction: KtFunction,
    override val parent: KoParentProvider?,
) :
    KoFunctionDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoExplicitReturnTypeProviderCore,
    KoExtensionProviderCore,
    KoImplementationProviderCore,
    KoKDocProviderCore,
    KoLocalClassProviderCore,
    KoLocalDeclarationProviderCore,
    KoLocalFunctionProviderCore,
    KoLocalPropertyProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoPackageProviderCore,
    KoParametersProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoReceiverTypeProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore,
    KoTopLevelProviderCore {
    override val ktFile: KtFile? by lazy { null }

    override val ktAnnotated: KtAnnotated by lazy { ktCallableDeclaration }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktCallableDeclaration }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktFunction }

    override val koFiles: List<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktFunction }

    override val ktElement: KtElement by lazy { ktFunction }

    override val hasImplementation: Boolean = ktFunction.hasBody()

    private val localDeclarationsHelper: List<KoBaseDeclaration> by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.toList()
            ?: emptyList()

        psiChildren
            .mapNotNull {
                if (it is KtClass && !it.isInterface()) {
                    KoClassDeclarationImpl.getInstance(it, this)
                } else if (it is KtFunction) {
                    getInstance(it, this)
                } else if (it is KtProperty) {
                    KoPropertyDeclarationImpl.getInstance(it, this)
                } else {
                    null
                }
            }
    }

    override val localDeclarations: List<KoBaseDeclaration>
        get() = localDeclarationsHelper

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {

        private val cache: KoDeclarationCache<KoFunctionDeclaration> = KoDeclarationCache()
        internal fun getInstance(ktFunction: KtFunction, parent: KoParentProvider?): KoFunctionDeclaration =
            cache.getOrCreateInstance(ktFunction, parent) {
                KoFunctionDeclarationImpl(ktFunction, parent)
            }
    }
}
