package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoSuspendModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoSuspendModifierProviderCore :
    KoSuspendModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    override val hasSuspendModifier: Boolean
        get() = hasModifier(KoModifier.SUSPEND)
}
