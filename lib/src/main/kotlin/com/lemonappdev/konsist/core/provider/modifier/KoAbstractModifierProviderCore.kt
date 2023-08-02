package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoAbstractModifierProviderCore :
    KoAbstractModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasAbstractModifier: Boolean
        get() = hasModifiers(KoModifier.ABSTRACT)
}
