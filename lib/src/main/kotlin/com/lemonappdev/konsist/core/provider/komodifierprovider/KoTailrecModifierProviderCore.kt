package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoTailrecModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoTailrecModifierProviderCore :
    KoTailrecModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasTailrecModifier: Boolean
        get() = hasModifiers(KoModifier.TAILREC)
}
