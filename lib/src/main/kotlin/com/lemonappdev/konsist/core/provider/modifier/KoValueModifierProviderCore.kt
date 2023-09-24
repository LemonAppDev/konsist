package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoValueModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoValueModifierProviderCore :
    KoValueModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasValueModifier: Boolean
        get() = hasModifier(KoModifier.VALUE)
}
