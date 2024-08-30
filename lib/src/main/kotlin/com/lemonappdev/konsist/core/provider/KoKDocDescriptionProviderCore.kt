package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoKDocDescriptionProvider

internal interface KoKDocDescriptionProviderCore :
    KoKDocDescriptionProvider,
    KoTextProviderCore,
    KoBaseProviderCore {
    override val description: String
        get() =
            text
                .substringBefore("@")
                .trimEnd()
}
