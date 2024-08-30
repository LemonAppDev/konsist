package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsNullableProvider

internal interface KoIsNullableProviderCore :
    KoIsNullableProvider,
    KoBaseProviderCore,
    KoTextProviderCore {
    override val isNullable: Boolean
        get() = text.last() == '?'
}
