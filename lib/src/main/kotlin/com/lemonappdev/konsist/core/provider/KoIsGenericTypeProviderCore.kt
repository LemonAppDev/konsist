package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider

@Deprecated("Will be removed in version 0.19.0", ReplaceWith("KoIsGenericProviderCore"))
internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoBaseProviderCore,
    KoIsGenericProviderCore {
    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("isGeneric"))
    override val isGenericType: Boolean
        get() = isGeneric
}
