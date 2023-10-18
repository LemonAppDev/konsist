package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import kotlin.reflect.KClass

internal interface KoExternalParentProviderCore :
    KoExternalParentProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    @Deprecated("Will be removed in v1.0.0.", replaceWith = ReplaceWith("externalParents()"))
    override val externalParents: List<KoExternalParentDeclaration>
        get() = parents()
            .filterIsInstance<KoExternalParentDeclaration>()

    @Deprecated("Will be removed in v1.0.0.", replaceWith = ReplaceWith("numExternalParents()"))
    override val numExternalParents: Int
        get() = externalParents.size

    override fun externalParents(indirectParents: Boolean): List<KoExternalParentDeclaration> = parents(indirectParents)
        .filterIsInstance<KoExternalParentDeclaration>()

    override fun numExternalParents(indirectParents: Boolean): Int = externalParents(indirectParents).size

    override fun countExternalParents(
        indirectParents: Boolean,
        predicate: (KoExternalParentDeclaration) -> Boolean
    ): Int =
        externalParents(indirectParents).count { predicate(it) }

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
        predicate: (KoExternalParentDeclaration) -> Boolean
    ): Boolean = externalParents(indirectParents).any(predicate)

    override fun hasAllExternalParents(
        indirectParents: Boolean,
        predicate: (KoExternalParentDeclaration) -> Boolean
    ): Boolean = externalParents(indirectParents).all(predicate)

    override fun hasExternalParentOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfParentOf(name, externalParents(indirectParents))
                || names.any { checkIfParentOf(it, externalParents(indirectParents)) }

    override fun hasAllExternalParentsOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfParentOf(name, externalParents(indirectParents))
                && names.all { checkIfParentOf(it, externalParents(indirectParents)) }
}
