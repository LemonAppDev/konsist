package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasDefaultNameProvider
import com.lemonappdev.konsist.core.util.CompanionUtil.COMPANION_NAME

internal interface KoHasDefaultNameProviderCore :
    KoBaseProviderCore,
    KoHasDefaultNameProvider,
    KoNameProviderCore {
    override val hasDefaultName: Boolean get() = name == COMPANION_NAME
}
