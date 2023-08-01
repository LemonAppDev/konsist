package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoCrossInlineModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoCrossInlineModifierProviderCore :
    KoCrossInlineModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasCrossInlineModifier: Boolean
        get() = hasModifiers(KoModifier.CROSSINLINE)
}
