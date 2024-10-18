package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationCore
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import kotlin.reflect.KClass

internal interface KoParentProviderCore :
    KoParentProvider,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override fun parents(indirectParents: Boolean): List<KoParentDeclaration> {
            val directParentDeclarations =  ktClassOrObject
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeListEntry>()
                ?.map { KoParentDeclarationCore.getInstance(it, containingDeclaration) }
                .orEmpty()

        val indirectParentDeclarations =
            if (indirectParents) getIndirectParents(directParentDeclarations) else emptyList()

        return (directParentDeclarations + indirectParentDeclarations).distinct()
    }

    private fun getIndirectParents(parents: List<KoParentDeclaration>): List<KoParentDeclaration> {
        val indirectParents = mutableListOf<KoParentDeclaration>()

        parents
            .forEach {
                val nextParents =
                    if (it.sourceDeclaration as? KoParentProvider != null) {
                        (it.sourceDeclaration as? KoParentProvider)?.parents(indirectParents = true) ?: emptyList()
                    } else {
                        emptyList()
                    }

                if (nextParents.isNotEmpty()) {
                    indirectParents += nextParents + getIndirectParents(nextParents)
                }
            }

        return indirectParents
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
    ): Boolean = hasParentWithName(listOf(name, *names), indirectParents)

    override fun hasParentWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParents(indirectParents)
            else ->
                names.any {
                    parents(indirectParents).any { parent -> it == parent.name }
                }
        }

    override fun hasParentsWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasParentsWithAllNames(listOf(name, *names), indirectParents)

    override fun hasParentsWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParents(indirectParents)
            else ->
                names.all {
                    parents(indirectParents).any { parent -> it == parent.name }
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
