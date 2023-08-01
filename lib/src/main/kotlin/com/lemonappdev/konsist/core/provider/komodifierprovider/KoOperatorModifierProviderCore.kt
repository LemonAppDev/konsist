package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOperatorModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoOperatorModifierProviderCore :
    KoOperatorModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasOperatorModifier: Boolean
        get() = hasModifiers(KoModifier.OPERATOR)
}
