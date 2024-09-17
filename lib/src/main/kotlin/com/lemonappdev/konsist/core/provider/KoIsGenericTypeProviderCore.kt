package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoTypeProviderCore"))
internal interface KoIsGenericTypeProviderCore :
    KoIsGenericTypeProvider,
    KoSourceAndAliasTypeProviderCore,
    KoBaseProviderCore
