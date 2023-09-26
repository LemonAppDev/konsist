package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoExpectModifierProviderCore :
    KoExpectModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasExpectModifier: Boolean
        get() = hasModifier(KoModifier.EXPECT)
}
