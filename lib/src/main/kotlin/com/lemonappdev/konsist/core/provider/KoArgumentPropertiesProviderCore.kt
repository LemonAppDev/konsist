package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoArgumentPropertiesProvider
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.KtValueArgument

internal interface KoArgumentPropertiesProviderCore : KoArgumentPropertiesProvider, KoBaseProviderCore {
    val ktValueArgument: KtValueArgument

    override val argumentName: String?
        get() = ktValueArgument
            .getArgumentName()
            ?.text

    override val value: String
        get() = ktValueArgument
                .getArgumentExpression()
                ?.text ?: ""
}
