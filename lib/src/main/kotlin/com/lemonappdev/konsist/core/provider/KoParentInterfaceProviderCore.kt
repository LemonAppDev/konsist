package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.ext.list.sourceDeclarations
import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import kotlin.reflect.KClass

internal interface KoParentInterfaceProviderCore :
    KoParentInterfaceProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override fun parentInterfaces(indirectParents: Boolean): List<KoParentDeclaration> =
        parents(indirectParents).filter { it.sourceDeclaration.isInterface }

    override fun numParentInterfaces(indirectParents: Boolean): Int = parentInterfaces(indirectParents).size

    override fun countParentInterfaces(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int = parentInterfaces(indirectParents).count { predicate(it) }

    override fun hasParentInterfaces(indirectParents: Boolean): Boolean = parentInterfaces(indirectParents).isNotEmpty()

    override fun hasParentInterfaceWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasParentInterfaceWithName(listOf(name, *names), indirectParents)

    override fun hasParentInterfaceWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentInterfaces(indirectParents)
            else ->
                names.any {
                    parentInterfaces(indirectParents).any { parentInterface -> it == parentInterface.name }
                }
        }

    override fun hasParentInterfacesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasParentInterfacesWithAllNames(listOf(name, *names), indirectParents)

    override fun hasParentInterfacesWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentInterfaces(indirectParents)
            else ->
                names.all {
                    parentInterfaces(indirectParents).any { parentInterface -> it == parentInterface.name }
                }
        }

    override fun hasParentInterface(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean = parentInterfaces(indirectParents).any(predicate)

    override fun hasAllParentInterfaces(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean = parentInterfaces(indirectParents).all(predicate)

    override fun hasParentInterfaceOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasParentInterfaceOf(listOf(name, *names), indirectParents)

    override fun hasParentInterfaceOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentInterfaces(indirectParents)
            else -> names.any { checkIfParentOf(it, parentInterfaces(indirectParents)) }
        }

    override fun hasAllParentInterfacesOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasAllParentInterfacesOf(listOf(name, *names), indirectParents)

    override fun hasAllParentInterfacesOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentInterfaces(indirectParents)
            else -> names.all { checkIfParentOf(it, parentInterfaces(indirectParents)) }
        }
}
