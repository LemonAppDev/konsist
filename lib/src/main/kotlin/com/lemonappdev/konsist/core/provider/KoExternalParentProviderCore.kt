package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.ext.list.sourceDeclarations
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import kotlin.reflect.KClass

internal interface KoExternalParentProviderCore :
    KoExternalParentProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override fun externalParents(indirectParents: Boolean): List<KoParentDeclaration> =
        parents(indirectParents).filter { it.sourceDeclaration?.isExternal == true }

    override fun numExternalParents(indirectParents: Boolean): Int = externalParents(indirectParents).size

    override fun countExternalParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int = externalParents(indirectParents).count { predicate(it) }

    override fun hasExternalParents(indirectParents: Boolean): Boolean = externalParents(indirectParents).isNotEmpty()

    override fun hasExternalParentWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasExternalParentWithName(listOf(name, *names), indirectParents)

    override fun hasExternalParentWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasExternalParents(indirectParents)
            else ->
                names.any {
                    externalParents(indirectParents).any { parentInterface -> it == parentInterface.name }
                }
        }

    override fun hasExternalParentsWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasExternalParentsWithAllNames(listOf(name, *names), indirectParents)

    override fun hasExternalParentsWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasExternalParents(indirectParents)
            else ->
                names.all {
                    externalParents(indirectParents).any { parentInterface -> it == parentInterface.name }
                }
        }

    override fun hasExternalParent(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean = externalParents(indirectParents).any(predicate)

    override fun hasAllExternalParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean = externalParents(indirectParents).all(predicate)

    override fun hasExternalParentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasExternalParentOf(listOf(name, *names), indirectParents)

    override fun hasExternalParentOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasExternalParents(indirectParents)
            else -> names.any { checkIfParentOf(it, externalParents(indirectParents)) }
        }

    override fun hasAllExternalParentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasAllExternalParentsOf(listOf(name, *names), indirectParents)

    override fun hasAllExternalParentsOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasExternalParents(indirectParents)
            else -> names.all { checkIfParentOf(it, externalParents(indirectParents)) }
        }

}
