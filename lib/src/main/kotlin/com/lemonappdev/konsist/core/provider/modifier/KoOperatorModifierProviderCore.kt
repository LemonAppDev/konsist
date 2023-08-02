package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoOperatorModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoOperatorModifierProviderCore :
    KoOperatorModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasOperatorModifier: Boolean
        get() = hasModifiers(KoModifier.OPERATOR)
}
