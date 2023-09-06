package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoConstructorDefinedProvider
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtParameter

internal interface KoConstructorDefinedProviderCore : KoConstructorDefinedProvider {
    val ktCallableDeclaration: KtCallableDeclaration

    override val isConstructorDefined: Boolean
        get() = ktCallableDeclaration is KtParameter
}
