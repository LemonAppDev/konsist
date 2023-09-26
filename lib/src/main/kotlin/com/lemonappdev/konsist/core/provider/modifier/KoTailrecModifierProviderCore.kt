package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoTailrecModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoTailrecModifierProviderCore :
    KoTailrecModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasTailrecModifier: Boolean
        get() = hasModifier(KoModifier.TAILREC)
}
