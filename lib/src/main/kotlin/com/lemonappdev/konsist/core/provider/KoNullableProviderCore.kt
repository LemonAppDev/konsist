package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableProvider

internal interface KoNullableProviderCore : KoNullableProvider, KoBaseProviderCore, KoTextProviderCore {
    override val isNullable: Boolean
        get() = text.last() == '?'
}
