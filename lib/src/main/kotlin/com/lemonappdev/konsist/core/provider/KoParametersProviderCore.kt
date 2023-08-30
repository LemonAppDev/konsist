package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationCore
import org.jetbrains.kotlin.psi.KtCallableDeclaration

internal interface KoParametersProviderCore :
    KoParametersProvider,
    KoContainingDeclarationProviderCore,
    KoKDocProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val parameters: List<KoParameterDeclaration>
        get() = ktCallableDeclaration
            .valueParameters
            .map { KoParameterDeclarationCore.getInstance(it, this) }

    override val numParameters: Int
        get() = parameters.size

    override fun hasParameterNamed(name: String): Boolean = parameters.any { it.name == name }
}
