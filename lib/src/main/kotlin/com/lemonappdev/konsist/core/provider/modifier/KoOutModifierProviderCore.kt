package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoOutModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoOutModifierProviderCore :
    KoOutModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasOutModifier: Boolean
        get() = hasModifier(KoModifier.OUT)
}
