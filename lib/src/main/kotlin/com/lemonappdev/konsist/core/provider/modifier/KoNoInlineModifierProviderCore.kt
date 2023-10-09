package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoNoInlineModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoNoInlineModifierProviderCore :
    KoNoInlineModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasNoInlineModifier: Boolean
        get() = hasModifiers(KoModifier.NOINLINE)
}
