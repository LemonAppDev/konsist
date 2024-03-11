package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtCallableDeclaration

internal interface KoParametersProviderCore :
    KoParametersProvider,
    KoContainingDeclarationProviderCore,
    KoKDocProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val parameters: List<KoParameterDeclaration>
        get() =
            ktCallableDeclaration
                .valueParameters
                .map { KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numParameters: Int
        get() = parameters.size

    override fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int = parameters.count { predicate(it) }

    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("hasParameterWithName(name)"))
    override fun hasParameterNamed(name: String): Boolean = parameters.any { it.name == name }

    override fun hasParameters(): Boolean = parameters.isNotEmpty()

    override fun hasParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            parameters.any { parameter -> it == parameter.name }
        }
    }

    override fun hasParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            parameters.any { parameter -> it == parameter.name }
        }
    }

    override fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameters.any(predicate)

    override fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameters.all(predicate)
}
