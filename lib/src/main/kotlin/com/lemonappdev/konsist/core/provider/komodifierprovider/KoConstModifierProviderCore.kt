package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoConstModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoConstModifierProviderCore :
    KoConstModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasConstModifier: Boolean
        get() = hasModifiers(KoModifier.CONST)
}
