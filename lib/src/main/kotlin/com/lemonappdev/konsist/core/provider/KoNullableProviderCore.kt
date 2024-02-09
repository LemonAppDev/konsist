package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableProvider
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType

internal interface KoNullableProviderCore : KoNullableProvider, KoBaseProviderCore {
    val ktUserType: KtUserType

    override val isNullable: Boolean
        get() = ktUserType.text.last() == '?'
}
