package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.core.util.TypeUtil

internal interface KoTypeProviderCore :
    KoTypeProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    override val isKotlinBasicType: Boolean
        get() = isKotlinType && TypeUtil.isKotlinBasicType(name)

    override val isKotlinCollectionType: Boolean
        get() = isKotlinType && TypeUtil.isKotlinCollectionTypes(name)
}
