package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsReadOnlyProvider

internal interface KoIsReadOnlyProviderCore :
    KoIsReadOnlyProvider,
    KoIsValProviderCore {
    override val isReadOnly: Boolean
        get() = isVal
}
