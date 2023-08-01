package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFunModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoFunModifierProviderCore :
    KoFunModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasFunModifier: Boolean
        get() = hasModifiers(KoModifier.FUN)
}
