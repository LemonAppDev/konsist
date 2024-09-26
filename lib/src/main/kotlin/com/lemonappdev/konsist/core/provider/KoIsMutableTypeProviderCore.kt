package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider

internal interface KoIsMutableTypeProviderCore :
    KoIsMutableTypeProvider,
    KoBaseProviderCore,
    KoNameProviderCore {
    override val isMutableType: Boolean
        get() = name.startsWith("Mutable")
}
