package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoVarArgModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoVarArgModifierProviderCore :
    KoVarArgModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasVarArgModifier: Boolean
        get() = hasModifiers(KoModifier.VARARG)
}
