package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoCrossInlineModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoCrossInlineModifierProviderCore :
    KoCrossInlineModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasCrossInlineModifier: Boolean
        get() = hasModifiers(KoModifier.CROSSINLINE)
}
