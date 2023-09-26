package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoDataModifierProviderCore :
    KoDataModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasDataModifier: Boolean
        get() = hasModifier(KoModifier.DATA)
}
