package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableProvider
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoNullableProviderCore : KoNullableProvider, KoBaseProviderCore {
    val ktTypeReference: KtTypeReference

    override val isNullable: Boolean
        get() = ktTypeReference.text.last() == '?'
}
