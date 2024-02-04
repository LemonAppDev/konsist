package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import com.lemonappdev.konsist.core.util.KClassUtil.checkIfKClassOf
import kotlin.reflect.KClass

internal interface KoExternalParentProviderCore :
    KoExternalParentProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override fun externalParents(indirectParents: Boolean): List<KoExternalParentDeclaration> = parents(indirectParents)
        .filterIsInstance<KoExternalParentDeclaration>()

    override fun numExternalParents(indirectParents: Boolean): Int = externalParents(indirectParents).size

    override fun countExternalParents(
        indirectParents: Boolean,
        predicate: (KoExternalParentDeclaration) -> Boolean,
    ): Int = externalParents(indirectParents).count { predicate(it) }

    override fun hasExternalParents(indirectParents: Boolean): Boolean = externalParents(indirectParents).isNotEmpty()

    override fun hasExternalParentWithName(name: String, vararg names: String, indirectParents: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            externalParents(indirectParents).any { parentInterface -> it == parentInterface.name }
        }
    }

    override fun hasExternalParentsWithAllNames(name: String, vararg names: String, indirectParents: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            externalParents(indirectParents).any { parentInterface -> it == parentInterface.name }
        }
    }

    override fun hasExternalParent(
        indirectParents: Boolean,
        predicate: (KoExternalParentDeclaration) -> Boolean,
    ): Boolean = externalParents(indirectParents).any(predicate)

    override fun hasAllExternalParents(
        indirectParents: Boolean,
        predicate: (KoExternalParentDeclaration) -> Boolean,
    ): Boolean = externalParents(indirectParents).all(predicate)

    override fun hasExternalParentOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfKClassOf(name, externalParents(indirectParents)) ||
            names.any { checkIfKClassOf(it, externalParents(indirectParents)) }

    override fun hasAllExternalParentsOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfKClassOf(name, externalParents(indirectParents)) &&
            names.all { checkIfKClassOf(it, externalParents(indirectParents)) }
}
