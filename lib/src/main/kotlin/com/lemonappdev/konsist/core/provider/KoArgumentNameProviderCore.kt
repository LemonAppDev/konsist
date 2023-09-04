package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoArgumentNameProvider
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentName

internal interface KoArgumentNameProviderCore : KoArgumentNameProvider, KoBaseProviderCore {
    val ktValueArgument: KtValueArgument

    override val argumentName: String?
        get() = ktValueArgument
            .getArgumentName()
            ?.text

    override val value: String
        get() {
            val expression = ktValueArgument
                .children
                .filterIsInstance<KtExpression>()
                .first()

            return if (expression is KtStringTemplateExpression) {
                expression
                    .children
                    .first()
                    .text

            } else {
                expression.text
            }
        }
}
