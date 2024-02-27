package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableProvider
import org.jetbrains.kotlin.psi.KtUserType

internal interface KoNullableProviderCore : KoNullableProvider, KoBaseProviderCore, KoTextProviderCore {
    override val isNullable: Boolean
        get() = text.last() == '?'
}
