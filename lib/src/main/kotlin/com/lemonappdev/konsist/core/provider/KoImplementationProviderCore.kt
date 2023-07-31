package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoImplementationProvider
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty

internal interface KoImplementationProviderCore : KoImplementationProvider, KoBaseProviderCore {
    val ktFunction: KtFunction?
    val ktProperty: KtProperty?

    override fun hasImplementation(): Boolean = ktFunction?.hasBody() ?: false || ktProperty?.hasBody() ?: false
}
