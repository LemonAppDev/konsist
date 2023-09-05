package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

internal interface KoBaseProviderCore : KoBaseProvider {
    override fun print(prefix: String?) {
        prefix?.let { println(it) }

        val text = if (this is KoNameProvider && name != "") name else toString()
        println(text)
    }
}
