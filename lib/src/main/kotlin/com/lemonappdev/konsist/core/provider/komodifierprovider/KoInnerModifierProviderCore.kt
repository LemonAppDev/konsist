package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoInnerModifierProviderCore :
    KoInnerModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasInnerModifier: Boolean
        get() = hasModifiers(KoModifier.INNER)
}
