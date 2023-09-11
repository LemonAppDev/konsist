package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoMatchesProvider
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoMatchesProviderCore : KoMatchesProvider, KoNameProviderCore, KoBaseProviderCore {
    override fun matches(element: String): Boolean = LocationUtil.resideInLocation(element, name)
}
