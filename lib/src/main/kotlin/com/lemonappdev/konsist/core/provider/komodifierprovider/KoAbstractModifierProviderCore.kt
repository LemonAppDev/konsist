package com.lemonappdev.konsist.core.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoAbstractModifierProviderCore :
    KoAbstractModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasAbstractModifier: Boolean
        get() = hasModifiers(KoModifier.ABSTRACT)
}
