package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.provider.modifier.KoValModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

@Deprecated("Will be removed in version 0.19.0", ReplaceWith("KoIsValProvider"))
internal interface KoValModifierProviderCore :
    KoValModifierProvider,
    KoBaseProviderCore
