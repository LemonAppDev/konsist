package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoInitializerProvider
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.psiUtil.hasBody

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsInitializedProviderCore"))
internal interface KoInitializerProviderCore : KoInitializerProvider, KoBaseProviderCore {
    val ktDeclaration: KtDeclaration

    override val isInitialized: Boolean
        get() = ktDeclaration.hasBody()
}
