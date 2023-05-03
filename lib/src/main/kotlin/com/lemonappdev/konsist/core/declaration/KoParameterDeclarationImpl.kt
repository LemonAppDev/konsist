package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstance
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal class KoParameterDeclarationImpl private constructor(private val ktParameter: KtParameter) :
    KoDeclarationImpl(ktParameter),
    KoParameterDeclaration {
    override val type by lazy {
        val type = ktParameter
            .children
            .firstIsInstance<KtTypeReference>()

        KoTypeDeclarationImpl.getInstance(type)
    }

    override val defaultValue by lazy {
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

    override fun hasVarargModifier() = hasModifiers(KoModifier.VARARG)

    override fun hasNoInlineModifier() = hasModifiers(KoModifier.NOINLINE)

    override fun hasCrossInlineModifier() = hasModifiers(KoModifier.CROSSINLINE)

    override fun hasDefaultValue(value: String?) = when (value) {
        null -> ktParameter.hasDefaultValue()
        else -> defaultValue == value
    }

    override fun representsType(type: String) = this.type.name == type || this.type.fullyQualifiedName == type

    internal companion object {
        private val cache = KoDeclarationCache<KoParameterDeclarationImpl>()

        internal fun getInstance(ktParameter: KtParameter) =
            cache.getOrCreateInstance(ktParameter) { KoParameterDeclarationImpl(ktParameter) }
    }
}
