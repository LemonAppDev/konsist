package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceAndObjectProvider
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider

internal interface KoClassAndInterfaceAndObjectProviderCore :
    KoClassAndInterfaceAndObjectProvider,
    KoBaseProviderCore,
    KoClassProviderCore,
    KoInterfaceProviderCore,
    KoObjectProviderCore {
    override fun classesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndInterfaceAndObjectDeclaration> =
        classes(includeNested, includeLocal) + interfaces(includeNested) + objects(includeNested)

    override fun numClassesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int = classesAndInterfacesAndObjects(includeNested, includeLocal).size

    override fun countClassesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Int = classesAndInterfacesAndObjects(includeNested, includeLocal).count { predicate(it) }

    override fun hasClassesOrInterfacesOrObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = classesAndInterfacesAndObjects(includeNested, includeLocal).isNotEmpty()

    override fun hasClassOrInterfaceOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassOrInterfaceOrObjectWithName(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassOrInterfaceOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClassesOrInterfacesOrObjects(includeNested, includeLocal)
            else ->
                names.any {
                    classesAndInterfacesAndObjects(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassesAndInterfacesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassesAndInterfacesAndObjectsWithAllNames(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassesAndInterfacesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClassesOrInterfacesOrObjects(includeNested, includeLocal)
            else ->
                names.all {
                    classesAndInterfacesAndObjects(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassOrInterfaceOrObject(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean = classesAndInterfacesAndObjects(includeNested, includeLocal).any(predicate)

    override fun hasAllClassesAndInterfacesAndObjects(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean = classesAndInterfacesAndObjects(includeNested, includeLocal).all(predicate)
}
