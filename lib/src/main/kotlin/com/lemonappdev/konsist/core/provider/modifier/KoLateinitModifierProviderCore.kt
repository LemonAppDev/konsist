package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoLateinitModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoLateinitModifierProviderCore :
    KoLateinitModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasLateinitModifier: Boolean
        get() = hasModifier(KoModifier.LATEINIT)
}
