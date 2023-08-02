package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoCompanionModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoCompanionModifierProviderCore :
    KoCompanionModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasCompanionModifier: Boolean
        get() = hasModifiers(KoModifier.COMPANION)
}
