package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import org.jetbrains.kotlin.psi.KtFunction

internal interface KoParametersProviderCore :
    KoParametersProvider,
    KoParentProvider {
    val ktFunction: KtFunction
    override val parameters: List<KoParameterDeclaration>
        get() = ktFunction
            .valueParameters
            .map { KoParameterDeclarationImpl.getInstance(it, this) }

    override fun hasParameterNamed(name: String): Boolean = parameters.any { it.name == name }
}
