package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider

internal interface KoParentProviderCore :
    KoParentProvider,
    KoParentClassProviderCore,
    KoParentInterfaceProviderCore,
    KoBaseProviderCore {
    override val parents: List<KoParentDeclaration>
        get() = listOfNotNull(parentClass as? KoParentDeclaration) + parentInterfaces

    override val numParents: Int
        get() = parents.size

    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> hasParentClass() || hasParentInterfaces()
        else -> names.all { hasParentClass(it) || hasParentInterfaces(it) }
    }
}
