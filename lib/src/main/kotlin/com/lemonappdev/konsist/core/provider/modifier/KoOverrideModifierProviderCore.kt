package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoOverrideModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoOverrideModifierProviderCore :
    KoOverrideModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasOverrideModifier: Boolean
        get() = hasModifiers(KoModifier.OVERRIDE)
}
