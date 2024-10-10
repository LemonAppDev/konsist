package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoTypeProjectionModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoTypeProjectionModifierProviderCore :
    KoTypeProjectionModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasInModifier: Boolean
        get() = hasModifier(KoModifier.IN)

    override val hasOutModifier: Boolean
        get() = hasModifier(KoModifier.OUT)
}
