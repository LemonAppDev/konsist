package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoParameterDeclaration private constructor(private val ktParameter: KtParameter) : KoDeclaration(ktParameter) {
    val type by lazy {
        val type = ktParameter
            .children
            .firstIsInstance<KtTypeReference>()

        KoTypeDeclaration.getInstance(type)
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

    fun hasVarargModifier() = hasModifiers(KoModifier.VARARG)

    fun hasNoInlineModifier() = hasModifiers(KoModifier.NOINLINE)

    fun hasCrossInlineModifier() = hasModifiers(KoModifier.CROSSINLINE)

    fun hasDefaultValue(value: String? = null) = when (value) {
        null -> ktParameter.hasDefaultValue()
        else -> defaultValue == value
    }

    fun hasType(type: String) = this.type.name == type || this.type.fullyQualifiedName == type

    inline fun <reified T>hasTypeOf() = T::class.simpleName == type.name

    companion object {
        private val cache = KoDeclarationCache<KoParameterDeclaration>()

        fun getInstance(ktParameter: KtParameter) =
            cache.getOrCreateInstance(ktParameter) { KoParameterDeclaration(ktParameter) }
    }
}
