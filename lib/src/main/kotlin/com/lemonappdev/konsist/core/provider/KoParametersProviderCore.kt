package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
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
                .map {
                    if (this is KoFullyQualifiedNameProvider) {
                        KoParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
                    } else {
                        KoParameterDeclarationCore.getInstance(it, (this as KoContainingDeclarationProvider).containingDeclaration)
                    }
                }

    override val numParameters: Int
        get() = parameters.size

    override fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int = parameters.count { predicate(it) }

    override fun hasParameters(): Boolean = parameters.isNotEmpty()

    override fun hasParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasParameterWithName(listOf(name, *names))

    override fun hasParameterWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasParameters()
            else ->
                names.any {
                    parameters.any { parameter -> it == parameter.name }
                }
        }

    override fun hasParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasParametersWithAllNames(listOf(name, *names))

    override fun hasParametersWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasParameters()
            else ->
                names.all {
                    parameters.any { parameter -> it == parameter.name }
                }
        }

    override fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameters.any(predicate)

    override fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean = parameters.all(predicate)
}
