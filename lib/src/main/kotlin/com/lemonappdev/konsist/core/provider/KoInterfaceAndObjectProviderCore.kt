package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceAndObjectProvider

internal interface KoInterfaceAndObjectProviderCore :
    KoInterfaceAndObjectProvider,
    KoBaseProviderCore,
    KoInterfaceProviderCore,
    KoObjectProviderCore {
    override fun interfacesAndObjects(includeNested: Boolean): List<KoInterfaceAndObjectDeclaration> =
        interfaces(includeNested) + objects(includeNested)

    override fun numInterfacesAndObjects(includeNested: Boolean): Int = interfacesAndObjects(includeNested).size

    override fun countInterfacesAndObjects(
        includeNested: Boolean,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Int = interfacesAndObjects(includeNested).count { predicate(it) }

    override fun hasInterfacesOrObjects(includeNested: Boolean): Boolean = interfacesAndObjects(includeNested).isNotEmpty()

    override fun hasInterfaceOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean = hasInterfaceOrObjectWithName(listOf(name, *names), includeNested, ignoreCase)

    override fun hasInterfaceOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasInterfacesOrObjects(includeNested)
            else ->
                names.any {
                    interfacesAndObjects(includeNested).any { koClass -> koClass.hasName(it, ignoreCase) }
                }
        }

    override fun hasInterfacesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean = hasInterfacesAndObjectsWithAllNames(listOf(name, *names), includeNested, ignoreCase)

    override fun hasInterfacesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasInterfacesOrObjects(includeNested)
            else ->
                names.all {
                    interfacesAndObjects(includeNested).any { koClass -> koClass.hasName(it, ignoreCase) }
                }
        }

    override fun hasInterfaceOrObject(
        includeNested: Boolean,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean = interfacesAndObjects(includeNested).any(predicate)

    override fun hasAllInterfacesAndObjects(
        includeNested: Boolean,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean = interfacesAndObjects(includeNested).all(predicate)
}
