package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoInfixModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoInfixModifierProviderCore :
    KoInfixModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasInfixModifier: Boolean
        get() = hasModifier(KoModifier.INFIX)
}
