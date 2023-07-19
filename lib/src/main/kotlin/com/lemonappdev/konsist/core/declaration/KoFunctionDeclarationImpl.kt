package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocalPropertyProvider
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoExplicitReturnTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoImplementationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

@Suppress("detekt.TooManyFunctions")
internal class KoFunctionDeclarationImpl private constructor(
    override val ktFunction: KtFunction,
    override val parentDeclaration: KoParentDeclarationProvider?,
) :
    KoBaseDeclarationImpl(ktFunction),
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoParametersProviderCore,
    KoFunctionDeclaration,
    KoLocalDeclarationProviderCore,
    KoLocalClassProviderCore,
    KoLocalFunctionProviderCore,
    KoLocalPropertyProviderCore,
    KoExtensionProviderCore,
    KoExplicitReturnTypeProviderCore,
    KoReceiverTypeProviderCore,
    KoImplementationProviderCore {
    override val ktFile: KtFile? by lazy { null }

    override val ktAnnotated: KtAnnotated by lazy { ktCallableDeclaration }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktCallableDeclaration }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktFunction }

    override val koFiles: Sequence<KoFile>? by lazy { null }

    private val localDeclarations: Sequence<KoBaseDeclaration> by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.asSequence()
            ?: emptySequence()

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

    override fun localDeclarations(): Sequence<KoBaseDeclaration> = localDeclarations

    override fun hasValidReturnTag(enabled: Boolean): Boolean = TagUtil.hasValidReturnTag(enabled, explicitReturnType?.name, kDoc)

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)

    override fun hasValidReceiverTag(enabled: Boolean): Boolean = TagUtil.hasValidReceiverTag(enabled, kDoc)

    internal companion object {

        private val cache: KoDeclarationCache<KoFunctionDeclaration> = KoDeclarationCache()
        internal fun getInstance(ktFunction: KtFunction, parentDeclaration: KoParentDeclarationProvider?): KoFunctionDeclaration =
            cache.getOrCreateInstance(ktFunction, parentDeclaration) {
                KoFunctionDeclarationImpl(ktFunction, parentDeclaration)
            }
    }
}
