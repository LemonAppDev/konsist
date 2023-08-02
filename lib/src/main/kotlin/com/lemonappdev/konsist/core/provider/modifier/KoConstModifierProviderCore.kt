package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoConstModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoConstModifierProviderCore :
    KoConstModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasConstModifier: Boolean
        get() = hasModifiers(KoModifier.CONST)
}
