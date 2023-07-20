package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

internal interface KoBaseProviderCore: KoBaseProvider {
    val errorText: String
        get() = this.toString()
}
