package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoNullableTypeProviderCore : KoNullableTypeProvider, KoBaseProviderCore {
    val ktTypeReference: KtTypeReference

    override val isNullable: Boolean
        get() = ktTypeReference.text.last() == '?'
}
