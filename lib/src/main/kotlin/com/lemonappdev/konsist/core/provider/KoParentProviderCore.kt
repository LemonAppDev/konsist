package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
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

    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("parents()"))
    override val parents: List<KoParentDeclaration>
        get() = parents()

    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("numParents()"))
    override val numParents: Int
        get() = parents.size

    override fun parents(indirectParents: Boolean): List<KoParentDeclaration> {
        val directParentDeclarations =
            ktClassOrObject
                .getSuperTypeList()
                ?.children
                ?.filterIsInstance<KtSuperTypeListEntry>()
                ?.map {
                    val name =
                        it
                            .text
                            .substringBefore(" ")
                            .substringBefore("(")
                            .substringBefore("<")

                    val fqn =
                        containingFile
                            .imports
                            .firstOrNull { import ->
                                import.name.substringAfterLast(".") == name || import.alias?.name == name
                            }
                            ?.name
                            ?: (containingFile.packagee?.fullyQualifiedName + "." + name)

                    return@map getClass(name, fqn, containingFile)
                        ?: getInterface(name, fqn, containingFile)
                        ?: KoExternalDeclarationCore.getInstance(name, it)
                }
                ?.toMutableList()
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
                    if (it as? KoParentProvider != null) {
                        it.parents(indirectParents = true)
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

    @Deprecated("Will be removed in v0.16.0.", ReplaceWith("hasParentsWithAllNames(*names)"))
    override fun hasParents(vararg names: String): Boolean =
        when {
            names.isEmpty() -> parents().isNotEmpty()
            else ->
                names.all {
                    parents().any { parent -> it == parent.name }
                }
        }

    override fun hasParents(indirectParents: Boolean): Boolean = parents(indirectParents).isNotEmpty()

    override fun hasParentWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasParentWithName(listOf(name, *names), indirectParents)

    override fun hasParentWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean = when {
        names.isEmpty() -> hasParents(indirectParents)
        else -> names.any {
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
    ): Boolean = when {
        names.isEmpty() -> hasParents(indirectParents)
        else -> names.all {
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
    ): Boolean = when {
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
    ): Boolean = when {
        names.isEmpty() -> hasParents(indirectParents)
        else -> names.all { checkIfParentOf(it, parents(indirectParents)) }
    }
}
