package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import org.jetbrains.kotlin.psi.KtCallableDeclaration

internal interface KoParametersProviderCore :
    KoParametersProvider,
    KoParentProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration
    override val parameters: Sequence<KoParameterDeclaration>
        get() = ktCallableDeclaration
            .valueParameters
            .map { KoParameterDeclarationImpl.getInstance(it, this) }
            .asSequence()

    override fun hasParameterNamed(name: String): Boolean = parameters.any { it.name == name }
}
