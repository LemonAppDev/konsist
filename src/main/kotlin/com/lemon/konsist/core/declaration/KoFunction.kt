package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtFunction

class KoFunction(private val ktFunction: KtFunction) : KoDeclaration(ktFunction) {

    val isOperator by lazy { ktFunction.modifierList?.hasModifier(KtTokens.OPERATOR_KEYWORD) ?: false }

    val isInline by lazy { ktFunction.modifierList?.hasModifier(KtTokens.INLINE_KEYWORD) ?: false }

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
}
