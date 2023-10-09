package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoEnumModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoEnumModifierProviderCore :
    KoEnumModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasEnumModifier: Boolean
        get() = hasModifier(KoModifier.ENUM)
}
