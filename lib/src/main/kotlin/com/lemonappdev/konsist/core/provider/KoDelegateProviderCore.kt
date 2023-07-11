package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDelegateProvider

internal interface KoDelegateProviderCore : KoDelegateProvider {
    override fun hasDelegate(delegateName: String?): Boolean = when (delegateName) {
        null -> this.delegateName != null
        else -> this.delegateName == delegateName
    }
}
