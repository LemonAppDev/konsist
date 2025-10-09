package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider

internal interface KoArgumentProviderCore :
    KoArgumentProvider,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {
    override val numArguments: Int
        get() = arguments.size

    override fun countArguments(predicate: (KoArgumentDeclaration) -> Boolean): Int = arguments.count { predicate(it) }

    override fun hasArguments(): Boolean = arguments.isNotEmpty()

    override fun hasArgumentWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasArgumentWithName(listOf(name, *names), ignoreCase)

    override fun hasArgumentWithName(names: Collection<String>, ignoreCase: Boolean,): Boolean =
        when {
            names.isEmpty() -> hasArguments()
            else ->
                names.any {
                    arguments.any { argument -> argument.name.equals(it, ignoreCase) }
                }
        }

    override fun hasArgumentsWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasArgumentsWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasArgumentsWithAllNames(names: Collection<String>, ignoreCase: Boolean,): Boolean =
        when {
            names.isEmpty() -> hasArguments()
            else ->
                names.all {
                    arguments.any { argument -> argument.name.equals(it, ignoreCase) }
                }
        }

    override fun hasArgument(predicate: (KoArgumentDeclaration) -> Boolean): Boolean = arguments.any(predicate)

    override fun hasAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): Boolean = arguments.all(predicate)
}
