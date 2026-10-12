package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoContextParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoContextParametersProvider
import com.lemonappdev.konsist.core.declaration.KoContextParameterDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtContextParameterList
import org.jetbrains.kotlin.psi.KtModifierListOwner

internal interface KoContextParametersProviderCore :
    KoContextParametersProvider,
    KoBaseProviderCore {
    val ktModifierListOwner: KtModifierListOwner

    override val contextParameters: List<KoContextParameterDeclaration>
        get() =
            ktModifierListOwner
                .modifierList
                ?.children
                ?.filterIsInstance<KtContextParameterList>()
                ?.flatMap { it.contextParameters() }
                ?.map { KoContextParameterDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
                .orEmpty()

    override val numContextParameters: Int
        get() = contextParameters.size

    override fun countContextParameters(predicate: (KoContextParameterDeclaration) -> Boolean): Int =
        contextParameters.count { predicate(it) }

    override fun hasContextParameters(): Boolean = contextParameters.isNotEmpty()

    override fun hasContextParameterWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasContextParameterWithName(listOf(name, *names), ignoreCase)

    override fun hasContextParameterWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> {
                hasContextParameters()
            }

            else -> {
                names.any {
                    contextParameters.any { parameter -> parameter.hasName(it, ignoreCase) }
                }
            }
        }

    override fun hasContextParametersWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasContextParametersWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasContextParametersWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> {
                hasContextParameters()
            }

            else -> {
                names.all {
                    contextParameters.any { parameter -> parameter.hasName(it, ignoreCase) }
                }
            }
        }

    override fun hasContextParameter(predicate: (KoContextParameterDeclaration) -> Boolean): Boolean = contextParameters.any(predicate)

    override fun hasAllContextParameters(predicate: (KoContextParameterDeclaration) -> Boolean): Boolean = contextParameters.all(predicate)
}
