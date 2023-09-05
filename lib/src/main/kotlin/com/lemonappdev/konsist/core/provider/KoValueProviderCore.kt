package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoValueProvider
import org.jetbrains.kotlin.psi.KtValueArgument

internal interface KoValueProviderCore : KoValueProvider, KoBaseProviderCore {
    val ktValueArgument: KtValueArgument

    override val value: String
        get() = ktValueArgument
            .getArgumentExpression()
            ?.text ?: ""
}
