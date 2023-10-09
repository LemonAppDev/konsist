package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoValueProvider
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.KtValueArgument

internal interface KoValueProviderCore : KoValueProvider, KoBaseProviderCore {
    val ktValueArgument: KtValueArgument

    override val value: String
        get() {
            val expression = ktValueArgument
                .getArgumentExpression()

            return if (expression is KtStringTemplateExpression) {
                expression
                    .children
                    .joinToString("") { it.text }
            } else if (expression != null) {
                expression.text
            } else {
                ""
            }
        }
}
