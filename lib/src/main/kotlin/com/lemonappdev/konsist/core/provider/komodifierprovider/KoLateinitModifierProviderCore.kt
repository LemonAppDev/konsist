package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoLateinitModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoLateinitModifierProviderCore :
    KoLateinitModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasLateinitModifier: Boolean
        get() = hasModifiers(KoModifier.LATEINIT)
}
