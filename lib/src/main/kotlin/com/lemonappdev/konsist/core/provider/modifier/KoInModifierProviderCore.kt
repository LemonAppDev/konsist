package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoInModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoInModifierProviderCore :
    KoInModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasInModifier: Boolean
        get() = hasModifier(KoModifier.IN)
}
