package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsInitializedProvider
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.psiUtil.hasBody

internal interface KoIsInitializedProviderCore :
    KoIsInitializedProvider,
    KoBaseProviderCore {
    val ktDeclaration: KtDeclaration

    override val isInitialized: Boolean
        get() = ktDeclaration.hasBody()
}
