package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsConstructorDefinedProvider
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtParameter

internal interface KoIsConstructorDefinedProviderCore : KoIsConstructorDefinedProvider, KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val isConstructorDefined: Boolean
        get() = ktCallableDeclaration is KtParameter
}
