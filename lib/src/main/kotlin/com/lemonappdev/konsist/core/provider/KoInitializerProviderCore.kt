package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoInitializerProvider
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.psiUtil.hasBody

internal interface KoInitializerProviderCore : KoInitializerProvider, KoBaseProviderCore {
    val ktDeclaration: KtDeclaration

    override val isInitialized: Boolean
        get() = ktDeclaration.hasBody()

    @Deprecated("Will be removed in v0.16.0", ReplaceWith("isInitialized"))
    override val hasImplementation: Boolean
        get() = ktDeclaration.hasBody()
}
