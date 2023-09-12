package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider

internal interface KoArgumentProviderCore :
    KoArgumentProvider,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {
    override val numArguments: Int
        get() = arguments.size

    override fun countArguments(predicate: (KoArgumentDeclaration) -> Boolean): Int =
        arguments.count { predicate(it) }

    override fun hasArguments(): Boolean = arguments.isNotEmpty()

    override fun hasArgumentWithName(vararg names: String): Boolean = names.any {
        arguments.any { argument -> it == argument.name }
    }

    override fun hasArgumentsWithAllNames(vararg names: String): Boolean = names.all {
        arguments.any { argument -> it == argument.name }
    }

    override fun hasArgument(predicate: (KoArgumentDeclaration) -> Boolean): Boolean = arguments.any(predicate)

    override fun hasAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): Boolean = arguments.all(predicate)
}
