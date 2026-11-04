package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoBodyProvider
import org.jetbrains.kotlin.psi.KtDeclarationWithBody

internal interface KoBodyProviderCore :
    KoBodyProvider,
    KoBaseProviderCore {
    val ktDeclarationWithBody: KtDeclarationWithBody

    override val hasBlockBody: Boolean
        get() = ktDeclarationWithBody.hasBody() && ktDeclarationWithBody.hasBlockBody()

    override val hasExpressionBody: Boolean
        get() = ktDeclarationWithBody.hasBody() && !ktDeclarationWithBody.hasBlockBody()

    override val body: String?
        get() =
            ktDeclarationWithBody
                .bodyBlockExpression
                ?.text
                ?.removePrefix("{")
                ?.removeSuffix("}")
                ?.trimIndent()
                ?.trim()
                ?: ktDeclarationWithBody.bodyExpression?.text
}
