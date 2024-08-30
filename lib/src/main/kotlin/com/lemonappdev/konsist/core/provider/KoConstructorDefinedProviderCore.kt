package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoConstructorDefinedProvider
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtParameter

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsConstructorDefinedProviderCore"))
internal interface KoConstructorDefinedProviderCore :
    KoConstructorDefinedProvider,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val isConstructorDefined: Boolean
        get() = ktCallableDeclaration is KtParameter
}
