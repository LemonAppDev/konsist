package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsReadOnlyProvider
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore

internal interface KoIsReadOnlyProviderCore : KoIsReadOnlyProvider, KoValModifierProviderCore {
    override val isReadOnly: Boolean
        get() = hasValModifier
}
