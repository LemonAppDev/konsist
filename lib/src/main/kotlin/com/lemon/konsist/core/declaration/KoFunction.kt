package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import com.lemon.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemon.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemon.konsist.core.declaration.provider.KoLocalPropertyProvider
import com.lemon.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

@Suppress("detekt.TooManyFunctions")
class KoFunction private constructor(private val ktFunction: KtFunction) :
    KoParametrizedDeclaration(ktFunction),
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider {

    private val localDeclarations by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.toList()
            ?: emptyList()

        psiChildren.mapNotNull {
            if (it is KtClass && !it.isInterface()) {
                KoClass.getInstance(it)
            } else if (it is KtFunction) {
                getInstance(it)
            } else if (it is KtProperty) {
                KoProperty.getInstance(it)
            } else {
                throw KoInternalException("Unknown local declaration type: ${it.getTextWithLocation()}")
            }
        }
    }

    val explicitReturnType by lazy {
        val type = ktFunction
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoType.getInstance(type) }
    }

    fun hasOperatorModifier() = ktFunction.modifierList?.hasModifier(KtTokens.OPERATOR_KEYWORD) ?: false

    fun hasInlineModifier() = ktFunction.modifierList?.hasModifier(KtTokens.INLINE_KEYWORD) ?: false

    fun hasTailrecModifier() = ktFunction.modifierList?.hasModifier(KtTokens.TAILREC_KEYWORD) ?: false

    fun hasInfixModifier() = ktFunction.modifierList?.hasModifier(KtTokens.INFIX_KEYWORD) ?: false

    fun hasExternalModifier() = ktFunction.modifierList?.hasModifier(KtTokens.EXTERNAL_KEYWORD) ?: false

    fun hasSuspendModifier() = ktFunction.modifierList?.hasModifier(KtTokens.SUSPEND_KEYWORD) ?: false

    fun hasOpenModifier() = ktFunction.modifierList?.hasModifier(KtTokens.OPEN_KEYWORD) ?: false

    fun hasOverrideModifier() = ktFunction.modifierList?.hasModifier(KtTokens.OVERRIDE_KEYWORD) ?: false

    fun hasFinalModifier() = ktFunction.modifierList?.hasModifier(KtTokens.FINAL_KEYWORD) ?: false

    fun hasAbstractModifier() = ktFunction.modifierList?.hasModifier(KtTokens.ABSTRACT_KEYWORD) ?: false

    fun hasActualModifier() = ktFunction.modifierList?.hasModifier(KtTokens.ACTUAL_KEYWORD) ?: false

    fun hasExpectModifier() = ktFunction.modifierList?.hasModifier(KtTokens.EXPECT_KEYWORD) ?: false

    fun isExtension() = ktFunction.isExtensionDeclaration()

    fun hasExplicitReturnType() = ktFunction.hasDeclaredReturnType()

    override fun localDeclarations(): List<KoDeclaration> = localDeclarations

    companion object {
        private val cache = KoDeclarationCache<KoFunction>()

        fun getInstance(ktFunction: KtFunction) = cache.getOrCreateInstance(ktFunction) { KoFunction(ktFunction) }
    }
}
