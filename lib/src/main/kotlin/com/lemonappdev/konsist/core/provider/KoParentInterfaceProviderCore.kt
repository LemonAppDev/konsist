package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import kotlin.reflect.KClass

internal interface KoParentInterfaceProviderCore :
    KoParentInterfaceProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override val parentInterfaces: List<KoInterfaceDeclaration>
        get() = parents.filterIsInstance<KoInterfaceDeclaration>()

    override val numParentInterfaces: Int
        get() = parentInterfaces.size

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentInterfaces(vararg names: String): Boolean = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }

    override fun countParentInterfaces(predicate: (KoInterfaceDeclaration) -> Boolean): Int =
        parentInterfaces.count { predicate(it) }

    override fun hasParentInterfaces(): Boolean = parentInterfaces.isNotEmpty()

    override fun hasParentInterfaceWithName(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            parentInterfaces.any { parentInterface -> it == parentInterface.name }
        }
    }

    override fun hasParentInterfacesWithAllNames(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            parentInterfaces.any { parentInterface -> it == parentInterface.name }
        }
    }

    override fun hasParentInterface(predicate: (KoInterfaceDeclaration) -> Boolean): Boolean = parentInterfaces.any(predicate)

    override fun hasAllParentInterfaces(predicate: (KoInterfaceDeclaration) -> Boolean): Boolean = parentInterfaces.all(predicate)

    override fun hasParentInterfaceOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
        checkIfParentOf(name, parentInterfaces) || names.any { checkIfParentOf(it, parentInterfaces) }

    override fun hasAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Boolean =
        checkIfParentOf(name, parentInterfaces) && names.all { checkIfParentOf(it, parentInterfaces) }
}
