package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoInnerModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoInnerModifierProviderCore :
    KoInnerModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasInnerModifier: Boolean
        get() = hasModifier(KoModifier.INNER)
}
