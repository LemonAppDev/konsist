package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider

internal interface KoClassAndInterfaceProviderCore :
    KoClassAndInterfaceProvider,
    KoBaseProviderCore,
    KoClassProviderCore,
    KoInterfaceProviderCore {
    override fun classesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassAndInterfaceDeclaration> =
        classes(
            includeNested,
            includeLocal,
        ).map { it as KoClassAndInterfaceDeclaration } + interfaces(includeNested).map { it as KoClassAndInterfaceDeclaration }

    override fun numClassesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int = classesAndInterfaces(includeNested, includeLocal).size

    override fun countClassesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Int = classesAndInterfaces(includeNested, includeLocal).count { predicate(it) }

    override fun hasClassesOrInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = classesAndInterfaces(includeNested, includeLocal).isNotEmpty()

    override fun hasClassOrInterfaceWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassOrInterfaceWithName(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassOrInterfaceWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClassesOrInterfaces(includeNested, includeLocal)
            else ->
                names.any {
                    classesAndInterfaces(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassesAndInterfacesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassesAndInterfacesWithAllNames(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassesAndInterfacesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClassesOrInterfaces(includeNested, includeLocal)
            else ->
                names.all {
                    classesAndInterfaces(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassOrInterface(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Boolean = classesAndInterfaces(includeNested, includeLocal).any(predicate)

    override fun hasAllClassesAndInterfaces(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Boolean = classesAndInterfaces(includeNested, includeLocal).all(predicate)
}
