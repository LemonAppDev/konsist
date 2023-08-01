package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExpectModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoExpectModifierProviderCore :
    KoExpectModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasExpectModifier: Boolean
        get() = hasModifiers(KoModifier.EXPECT)
}
