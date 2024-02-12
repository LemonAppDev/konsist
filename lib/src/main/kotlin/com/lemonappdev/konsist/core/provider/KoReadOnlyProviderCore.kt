package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoReadOnlyProvider
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore

internal interface KoReadOnlyProviderCore : KoReadOnlyProvider, KoValModifierProviderCore {

    override val isReadOnly: Boolean
        get() = hasValModifier
}
