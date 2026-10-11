package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import kotlin.reflect.KClass

internal interface KoParentProviderCore :
    KoParentProvider,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override fun parents(indirectParents: Boolean): List<KoParentDeclaration> {
        val directParentDeclarations = getDirectParents()

        val parentDeclarations =
            if (indirectParents) {
                directParentDeclarations + getIndirectParents(directParentDeclarations, mutableSetOf(this))
            } else {
                directParentDeclarations
            }

        return parentDeclarations.distinctBy { it.sourceDeclaration }
    }

    private fun getDirectParents(): List<KoParentDeclaration> =
        ktClassOrObject
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
            .orEmpty()

    /*
     * Parents of every declaration are searched once, so the search time grows linearly with the size of the hierarchy
     * (instead of exponentially with its depth) and the search ends for the cyclic hierarchy (invalid code, e.g.
     * `class A : B()` and `class B : A()`). Parents found again would be removed as duplicates anyway.
     */
    private fun getIndirectParents(
        parents: List<KoParentDeclaration>,
        visitedDeclarations: MutableSet<KoParentProviderCore>,
    ): List<KoParentDeclaration> =
        parents.flatMap { parent ->
            val sourceDeclaration = parent.sourceDeclaration as? KoParentProviderCore

            if (sourceDeclaration == null || !visitedDeclarations.add(sourceDeclaration)) {
                emptyList()
            } else {
                val nextParents = sourceDeclaration.getDirectParents()
                nextParents + getIndirectParents(nextParents, visitedDeclarations)
            }
        }

    override fun numParents(indirectParents: Boolean): Int = parents(indirectParents).size

    override fun countParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Int = parents(indirectParents).count { predicate(it) }

    override fun hasParents(indirectParents: Boolean): Boolean = parents(indirectParents).isNotEmpty()

    override fun hasParentWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
        ignoreCase: Boolean,
    ): Boolean = hasParentWithName(listOf(name, *names), indirectParents, ignoreCase)

    override fun hasParentWithName(
        names: Collection<String>,
        indirectParents: Boolean,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> {
                hasParents(indirectParents)
            }

            else -> {
                names.any {
                    parents(indirectParents).any { parent -> parent.hasName(it, ignoreCase) }
                }
            }
        }

    override fun hasParentsWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
        ignoreCase: Boolean,
    ): Boolean = hasParentsWithAllNames(listOf(name, *names), indirectParents, ignoreCase)

    override fun hasParentsWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> {
                hasParents(indirectParents)
            }

            else -> {
                names.all {
                    parents(indirectParents).any { parent -> parent.hasName(it, ignoreCase) }
                }
            }
        }

    override fun hasParent(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean = parents(indirectParents).any(predicate)

    override fun hasAllParents(
        indirectParents: Boolean,
        predicate: (KoParentDeclaration) -> Boolean,
    ): Boolean = parents(indirectParents).all(predicate)

    override fun hasParentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasParentOf(listOf(name, *names), indirectParents)

    override fun hasParentOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParents(indirectParents)
            else -> names.any { checkIfParentOf(it, parents(indirectParents)) }
        }

    override fun hasAllParentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasAllParentsOf(listOf(name, *names), indirectParents)

    override fun hasAllParentsOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParents(indirectParents)
            else -> names.all { checkIfParentOf(it, parents(indirectParents)) }
        }
}
