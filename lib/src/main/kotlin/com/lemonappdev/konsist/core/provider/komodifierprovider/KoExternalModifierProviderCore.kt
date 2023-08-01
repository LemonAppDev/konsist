package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExternalModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoExternalModifierProviderCore :
    KoExternalModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasExternalModifier: Boolean
        get() = hasModifiers(KoModifier.EXTERNAL)
}
