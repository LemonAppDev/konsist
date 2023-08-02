package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoActualModifierProviderCore :
    KoActualModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasActualModifier: Boolean
        get() = hasModifiers(KoModifier.ACTUAL)
}
