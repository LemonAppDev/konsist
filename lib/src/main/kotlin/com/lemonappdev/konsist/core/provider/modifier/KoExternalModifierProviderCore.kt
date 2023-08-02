package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoExternalModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoExternalModifierProviderCore :
    KoExternalModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasExternalModifier: Boolean
        get() = hasModifiers(KoModifier.EXTERNAL)
}
