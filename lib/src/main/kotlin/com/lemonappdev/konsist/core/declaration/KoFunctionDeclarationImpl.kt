package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.util.ReceiverUtil
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

@Suppress("detekt.TooManyFunctions")
internal class KoFunctionDeclarationImpl private constructor(private val ktFunction: KtFunction, parentDeclaration: KoParentProvider?) :
    KoParametrizedDeclarationImpl(ktFunction, parentDeclaration),
    KoFunctionDeclaration,
    KoModifierProviderCore {

    private val localDeclarations: Sequence<KoBaseDeclaration>
        get() {
            val psiChildren = ktFunction
                .bodyBlockExpression
                ?.children
                ?.asSequence()
                ?: emptySequence()

            return psiChildren
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

    override val returnType: KoTypeDeclaration? by lazy { ReceiverUtil.getType(getTypeReferences(), isExtension(), this) }

    override val receiverType: KoTypeDeclaration? by lazy { ReceiverUtil.getReceiverType(getTypeReferences(), isExtension(), this) }

    private fun getTypeReferences(): List<KtTypeReference> = ktFunction
        .children
        .filterIsInstance<KtTypeReference>()

    override fun isExtension(): Boolean = ktFunction.isExtensionDeclaration()

    override fun hasReceiverType(name: String?): Boolean = ReceiverUtil.hasReceiverType(receiverType, name)

    override fun hasReturnType(): Boolean = ktFunction.hasDeclaredReturnType()

    override fun localDeclarations(): Sequence<KoBaseDeclaration> = localDeclarations

    override fun hasValidReturnTag(enabled: Boolean): Boolean = TagUtil.hasValidReturnTag(enabled, returnType?.name, kDoc)

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)

    override fun hasValidReceiverTag(enabled: Boolean): Boolean = TagUtil.hasValidReceiverTag(enabled, kDoc)

    internal companion object {
        private val cache: KoDeclarationCache<KoFunctionDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktFunction: KtFunction, parentDeclaration: KoParentProvider?): KoFunctionDeclaration =
            cache.getOrCreateInstance(ktFunction, parentDeclaration) {
                KoFunctionDeclarationImpl(ktFunction, parentDeclaration)
            }
    }
}
