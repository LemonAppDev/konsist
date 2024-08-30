package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoReadOnlyProvider
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsReadOnlyProviderCore"))
internal interface KoReadOnlyProviderCore : KoReadOnlyProvider, KoValModifierProviderCore {
    override val isReadOnly: Boolean
        get() = hasValModifier
}
