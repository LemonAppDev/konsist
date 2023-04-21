package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoParameter private constructor(private val ktParameter: KtParameter) : KoDeclaration(ktParameter) {
    val type by lazy {
        val type = ktParameter
            .children
            .firstIsInstance<KtTypeReference>()

        KoType.getInstance(type)
    }

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

    fun hasVarargModifier() = ktParameter.isVarArg

    fun hasNoInlineModifier() = ktParameter.modifierList?.hasModifier(KtTokens.NOINLINE_KEYWORD) ?: false

    fun hasCrossInlineModifier() = ktParameter.modifierList?.hasModifier(KtTokens.CROSSINLINE_KEYWORD) ?: false

    fun hasDefaultValue(value: String? = null) = when (value) {
        null -> ktParameter.hasDefaultValue()
        else -> defaultValue == value
    }

    fun hasType(type: String) = this.type.name == type || this.type.fullyQualifiedName == type

    inline fun <reified T>hasType() = T::class.simpleName == type.name

    companion object {
        private val cache = KoDeclarationCache<KoParameter>()

        fun getInstance(ktParameter: KtParameter) =
            cache.getOrCreateInstance(ktParameter) { KoParameter(ktParameter) }
    }
}
