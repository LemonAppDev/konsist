package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoInterfaceProviderCore : KoInterfaceProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun interfaces(includeNested: Boolean): List<KoInterfaceDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
        )

    override fun numInterfaces(includeNested: Boolean): Int = interfaces(includeNested).size

    override fun countInterfaces(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Int = interfaces(includeNested).count { predicate(it) }

    override fun hasInterfaces(includeNested: Boolean): Boolean = interfaces(includeNested).isNotEmpty()

    override fun hasInterfaceWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean = hasInterfaceWithName(listOf(name, *names), includeNested)

    override fun hasInterfaceWithName(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasInterfaces(includeNested)
            else ->
                names.any {
                    interfaces(includeNested).any { koInterface -> it == koInterface.name }
                }
        }

    override fun hasInterfacesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean = hasInterfacesWithAllNames(listOf(name, *names), includeNested)

    override fun hasInterfacesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasInterfaces(includeNested)
            else ->
                names.all {
                    interfaces(includeNested).any { koInterface -> it == koInterface.name }
                }
        }

    override fun hasInterface(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean = interfaces(includeNested).any(predicate)

    override fun hasAllInterfaces(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean = interfaces(includeNested).all(predicate)
}
