package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoInlineModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoInlineModifierProviderCore :
    KoInlineModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasInlineModifier: Boolean
        get() = hasModifiers(KoModifier.INLINE)
}
