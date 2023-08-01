package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOverrideModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoOverrideModifierProviderCore :
    KoOverrideModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasOverrideModifier: Boolean
        get() = hasModifiers(KoModifier.OVERRIDE)
}
