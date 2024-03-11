package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import kotlin.reflect.KClass

internal interface KoParentInterfaceProviderCore :
    KoParentInterfaceProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("parentInterfaces()"))
    override val parentInterfaces: List<KoInterfaceDeclaration>
        get() = parents().filterIsInstance<KoInterfaceDeclaration>()

    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("numParentInterfaces()"))
    override val numParentInterfaces: Int
        get() = parentInterfaces.size

    override fun parentInterfaces(indirectParents: Boolean): List<KoInterfaceDeclaration> =
        parents(indirectParents).filterIsInstance<KoInterfaceDeclaration>()

    override fun numParentInterfaces(indirectParents: Boolean): Int = parentInterfaces(indirectParents).size

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentInterfaces(vararg names: String): Boolean =
        when {
            names.isEmpty() -> parentInterfaces.isNotEmpty()
            else ->
                names.all {
                    parentInterfaces.any { koParent -> it == koParent.name }
                }
        }

    override fun countParentInterfaces(
        indirectParents: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Int = parentInterfaces(indirectParents).count { predicate(it) }

    override fun hasParentInterfaces(indirectParents: Boolean): Boolean = parentInterfaces(indirectParents).isNotEmpty()

    override fun hasParentInterfaceWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            parentInterfaces(indirectParents).any { parentInterface -> it == parentInterface.name }
        }
    }

    override fun hasParentInterfacesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            parentInterfaces(indirectParents).any { parentInterface -> it == parentInterface.name }
        }
    }

    override fun hasParentInterface(
        indirectParents: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean = parentInterfaces(indirectParents).any(predicate)

    override fun hasAllParentInterfaces(
        indirectParents: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean = parentInterfaces(indirectParents).all(predicate)

    override fun hasParentInterfaceOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean =
        checkIfParentOf(name, parentInterfaces(indirectParents)) ||
            names.any { checkIfParentOf(it, parentInterfaces(indirectParents)) }

    override fun hasAllParentInterfacesOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean =
        checkIfParentOf(name, parentInterfaces(indirectParents)) &&
            names.all { checkIfParentOf(it, parentInterfaces(indirectParents)) }
}
