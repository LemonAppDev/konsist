package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoArgumentProvider

internal interface KoArgumentProviderCore : KoArgumentProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    override val numArguments: Int
        get() = arguments.size

    override fun hasArguments(): Boolean = arguments.isNotEmpty()
}
