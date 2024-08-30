package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoValueProvider
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtStringTemplateExpression

internal interface KoValueProviderCore :
    KoValueProvider,
    KoBaseProviderCore {
    val ktExpression: KtExpression?

    override val value: String?
        get() =
            if (ktExpression is KtStringTemplateExpression) {
                ktExpression
                    ?.children
                    ?.joinToString("") { it.text }
            } else {
                ktExpression?.text
            }

    override fun hasValue(value: String?): Boolean =
        when (value) {
            null -> this.value != null
            else -> this.value == value
        }
}
