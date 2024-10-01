package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

@Deprecated("Will be removed in version 0.19.0", ReplaceWith("koScope.functions()"))
internal interface KoFunModifierProviderCore :
    KoFunModifierProvider,
    KoBaseProviderCore,
    KoModifierProviderCore {
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("koScope.functions()"))
    override val hasFunModifier: Boolean
        get() = hasModifier(KoModifier.FUN)
}
