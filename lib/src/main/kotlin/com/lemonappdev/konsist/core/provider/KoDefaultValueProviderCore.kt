package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoDefaultValueProviderCore : KoDefaultValueProvider, KoBaseProviderCore {
    val ktParameter: KtParameter

    override val defaultValue: String?
        get() {
            // e.g. primitive value as default parameter value
            val constantExpressionText =
                ktParameter
                    .children
                    .firstIsInstanceOrNull<KtConstantExpression>()
                    ?.text

            if (constantExpressionText != null) {
                return constantExpressionText
            }

            // e.g. function call as default parameter value

            return ktParameter
                .children
                .firstIsInstanceOrNull<KtCallExpression>()
                ?.text
        }

    override fun hasDefaultValue(value: String?): Boolean =
        when (value) {
            null -> ktParameter.hasDefaultValue()
            else -> defaultValue == value
        }
}
