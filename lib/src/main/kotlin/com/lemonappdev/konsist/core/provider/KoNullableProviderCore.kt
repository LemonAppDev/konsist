package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoNullableProvider

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsNullableProviderCore"))
internal interface KoNullableProviderCore : KoNullableProvider, KoBaseProviderCore, KoTextProviderCore {
    override val isNullable: Boolean
        get() = text.last() == '?'
}
