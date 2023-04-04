package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoParameter private constructor(private val ktParameter: KtParameter) : KoDeclaration(ktParameter) {
    val type by lazy {
        ktParameter
            .children
            .firstIsInstance<KtTypeReference>()
            .text
    }

    val hasDefaultValue by lazy { ktParameter.hasDefaultValue() }

    val defaultValue by lazy {
        // eg. primitive value as default parameter value
        val constantExpressionText = ktParameter
            .children
            .firstIsInstanceOrNull<KtConstantExpression>()
            ?.text

        if (constantExpressionText != null) {
            return@lazy constantExpressionText
        }

        // eg. function call as default parameter value
        val callExpressionText = ktParameter
            .children
            .firstIsInstanceOrNull<KtCallExpression>()
            ?.text

        callExpressionText
    }

    companion object {
        private val cache = KoDeclarationCache<KoParameter>()
        fun getInstance(ktParameter: KtParameter) = if (cache.hasKey(ktParameter)) {
            cache.get(ktParameter)
        } else {
            cache.set(ktParameter, KoParameter(ktParameter))
            cache.get(ktParameter)
        }
    }
}
