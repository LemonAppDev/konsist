package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFinalModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoFinalModifierProviderCore :
    KoFinalModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasFinalModifier: Boolean
        get() = hasModifiers(KoModifier.FINAL)
}
