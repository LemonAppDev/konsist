package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal class KoFunctionDeclarationImpl private constructor(private val ktFunction: KtFunction, parent: KoBaseDeclaration?) :
    KoParametrizedDeclarationImpl(ktFunction, parent),
    KoFunctionDeclaration {

    private val localDeclarations by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.asSequence()
            ?: emptySequence()

        psiChildren.mapNotNull {
            if (it is KtClass && !it.isInterface()) {
                KoClassDeclarationImpl.getInstance(it, this)
            } else if (it is KtFunction) {
                getInstance(it, this)
            } else if (it is KtProperty) {
                KoPropertyDeclarationImpl.getInstance(it, this)
            } else {
                throw UnsupportedOperationException("Unknown local declaration type: ${it.getTextWithLocation()}")
            }
        }
    }

    override val returnType by lazy {
        val type = ktFunction
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoTypeDeclarationImpl.getInstance(type, this) }
    }

    override fun hasOperatorModifier() = hasModifiers(KoModifier.OPERATOR)

    override fun hasInlineModifier() = hasModifiers(KoModifier.INLINE)

    override fun hasTailrecModifier() = hasModifiers(KoModifier.TAILREC)

    override fun hasInfixModifier() = hasModifiers(KoModifier.INFIX)

    override fun hasExternalModifier() = hasModifiers(KoModifier.EXTERNAL)

    override fun hasSuspendModifier() = hasModifiers(KoModifier.SUSPEND)

    override fun hasOpenModifier() = hasModifiers(KoModifier.OPEN)

    override fun hasOverrideModifier() = hasModifiers(KoModifier.OVERRIDE)

    override fun hasFinalModifier() = hasModifiers(KoModifier.FINAL)

    override fun hasAbstractModifier() = hasModifiers(KoModifier.ABSTRACT)

    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    override fun isExtension() = ktFunction.isExtensionDeclaration()

    override fun hasReturnType() = ktFunction.hasDeclaredReturnType()

    override fun localDeclarations(): Sequence<KoDeclarationImpl> = localDeclarations

    override fun hasCompleteKDoc(
        verifyDescription: Boolean,
        verifyParamTag: Boolean,
        verifyReturnTag: Boolean,
        verifyConstructorTag: Boolean,
        verifyReceiverTag: Boolean,
        verifyPropertyTag: Boolean,
        verifyThrowsTag: Boolean,
        verifyExceptionTag: Boolean,
        verifySampleTag: Boolean,
        verifySeeTag: Boolean,
        verifyAuthorTag: Boolean,
        verifySinceTag: Boolean,
        verifySuppressTag: Boolean,
        verifyVersionTag: Boolean,
        verifyPropertySetterTag: Boolean,
        verifyPropertyGetterTag: Boolean
    ): Boolean {
        val paramValue = if (verifyParamTag && parameters != null) {
            parameters
                ?.all {
                    kDoc
                        ?.paramTags
                        ?.any { tag -> tag.value == it.name }
                        ?: false
                } ?: false
        } else {
            true
        }

        val returnValue = if (verifyReturnTag) {
            kDoc?.returnTag != null
        } else {
            true
        }

        val descriptionValue = super.hasCompleteKDoc(
            verifyDescription, verifyParamTag, verifyReturnTag, verifyConstructorTag, verifyReceiverTag,
            verifyPropertyTag, verifyThrowsTag, verifyExceptionTag, verifySampleTag, verifySeeTag, verifyAuthorTag,
            verifySinceTag, verifySuppressTag, verifyVersionTag, verifyPropertySetterTag, verifyPropertyGetterTag
        )

        return descriptionValue && paramValue && returnValue
    }

    internal companion object {
        private val cache = KoDeclarationCache<KoFunctionDeclarationImpl>()

        internal fun getInstance(ktFunction: KtFunction, parent: KoBaseDeclaration) = cache.getOrCreateInstance(ktFunction, parent) {
            KoFunctionDeclarationImpl(ktFunction, parent)
        }
    }
}
