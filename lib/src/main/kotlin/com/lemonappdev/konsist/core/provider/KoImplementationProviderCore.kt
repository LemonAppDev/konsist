package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoImplementationProvider
import org.jetbrains.kotlin.psi.KtFunction

internal interface KoImplementationProviderCore: KoImplementationProvider {
    val ktFunction: KtFunction

    override fun hasImplementation(): Boolean = ktFunction.hasBody()
}
