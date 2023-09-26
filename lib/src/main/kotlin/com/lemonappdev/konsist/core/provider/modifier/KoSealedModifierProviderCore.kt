package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoSealedModifierProviderCore :
    KoSealedModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasSealedModifier: Boolean
        get() = hasModifier(KoModifier.SEALED)
}
