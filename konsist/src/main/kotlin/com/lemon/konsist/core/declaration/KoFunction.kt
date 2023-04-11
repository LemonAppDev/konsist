package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import com.lemon.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemon.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemon.konsist.core.declaration.provider.KoLocalPropertyProvider
import com.lemon.konsist.exception.KoInternalException
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoFunction private constructor(private val ktFunction: KtFunction) :
    KoParametrizedDeclaration(ktFunction),
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider {

    val isOperator by lazy { ktFunction.modifierList?.hasModifier(KtTokens.OPERATOR_KEYWORD) ?: false }

    val isInline by lazy { ktFunction.modifierList?.hasModifier(KtTokens.INLINE_KEYWORD) ?: false }

    val isTailrec by lazy { ktFunction.modifierList?.hasModifier(KtTokens.TAILREC_KEYWORD) ?: false }

    val isInfix by lazy { ktFunction.modifierList?.hasModifier(KtTokens.INFIX_KEYWORD) ?: false }

    val isExternal by lazy { ktFunction.modifierList?.hasModifier(KtTokens.EXTERNAL_KEYWORD) ?: false }

    val isSuspend by lazy { ktFunction.modifierList?.hasModifier(KtTokens.SUSPEND_KEYWORD) ?: false }

    val isOpen by lazy { ktFunction.modifierList?.hasModifier(KtTokens.OPEN_KEYWORD) ?: false }

    val isOverride by lazy { ktFunction.modifierList?.hasModifier(KtTokens.OVERRIDE_KEYWORD) ?: false }

    val isFinal by lazy { ktFunction.modifierList?.hasModifier(KtTokens.FINAL_KEYWORD) ?: false }

    val isAbstract by lazy { ktFunction.modifierList?.hasModifier(KtTokens.ABSTRACT_KEYWORD) ?: false }

    val isExtension by lazy { ktFunction.isExtensionDeclaration() }

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

    val hasExplicitReturnType by lazy { ktFunction.hasDeclaredReturnType() }

    val explicitReturnType by lazy {
        val type = ktFunction
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoType.getInstance(type) }
    }

    override fun localDeclarations(): List<KoDeclaration> = localDeclarations

    companion object {
        private val cache = KoDeclarationCache<KoFunction>()

        fun getInstance(ktFunction: KtFunction) = cache.getOrCreateInstance(ktFunction) { KoFunction(ktFunction) }
    }
}
