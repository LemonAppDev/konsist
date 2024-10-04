package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoFunModifierProviderCore :
    KoFunModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasFunModifier: Boolean
        get() = hasModifier(KoModifier.FUN)
}
