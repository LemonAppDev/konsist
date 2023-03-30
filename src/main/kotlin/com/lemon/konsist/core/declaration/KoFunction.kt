package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoFunction(private val ktFunction: KtFunction) : KoDeclaration(ktFunction) {

    val isOperator by lazy { ktFunction.modifierList?.hasModifier(KtTokens.OPERATOR_KEYWORD) ?: false }

    val isInline by lazy { ktFunction.modifierList?.hasModifier(KtTokens.INLINE_KEYWORD) ?: false }

    val parameters by lazy {
        ktFunction
            .children
            .first()
            .children
            .filterIsInstance<KtParameter>()
            .map { KoParameter(it) }
    }

    fun hasParameterNamed(name: String) = parameters.firstOrNull()?.name == name

    val hasExplicitReturnType by lazy { ktFunction.hasDeclaredReturnType() }

    val getExplicitReturnType by lazy {
        ktFunction
            .children
            .firstIsInstanceOrNull<KtTypeReference>()
            ?.text
    }

    fun getLocalFunctions(includeNested: Boolean = false): List<KoFunction> {
        val koFunctions = (
            ktFunction
                .bodyBlockExpression
                ?.children
                ?.filterIsInstance<KtFunction>()
                ?.map { KoFunction(it) }
                ?: emptyList()
            )

        return if (includeNested) {
            koFunctions.flatMap { listOf(it) + it.getLocalFunctions(true) }
        } else {
            koFunctions
        }
    }

    fun getLocalProperties(): List<KoProperty> =
        ktFunction
            .bodyBlockExpression
            ?.children
            ?.filterIsInstance<KtProperty>()
            ?.map { KoProperty(it) }
            ?: emptyList()
}
