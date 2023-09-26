package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoOpenModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoOpenModifierProviderCore :
    KoOpenModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasOpenModifier: Boolean
        get() = hasModifier(KoModifier.OPEN)
}
