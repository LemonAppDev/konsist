package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider

internal interface KoGenericTypeProviderCore : KoGenericTypeProvider, KoSourceAndAliasTypeProviderCore, KoBaseProviderCore {

    override val isGenericType: Boolean
        get() {
            val regex = "\\w+<[^<>]+>".toRegex()

            return regex.matches(sourceType)
        }
}
