package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoEnumModifierProviderCore :
    KoEnumModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasEnumModifier: Boolean
        get() = hasModifiers(KoModifier.ENUM)
}
