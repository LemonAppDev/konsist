package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarArgModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoVarArgModifierProviderCore :
    KoVarArgModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasVarArgModifier: Boolean
        get() = hasModifiers(KoModifier.VARARG)
}
