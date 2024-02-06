package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoExternalParentDeclarationCore
import com.lemonappdev.konsist.core.model.DataCore
import com.lemonappdev.konsist.core.util.KClassUtil.checkIfKClassOf
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import kotlin.reflect.KClass

internal interface KoParentProviderCore :
    KoParentProvider,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    @Deprecated("Will be removed in v1.0.0.", replaceWith = ReplaceWith("parents()"))
    override val parents: List<KoParentDeclaration>
        get() = parents()

    @Deprecated("Will be removed in v1.0.0.", replaceWith = ReplaceWith("numParents()"))
    override val numParents: Int
        get() = parents.size

    override fun parents(indirectParents: Boolean): List<KoParentDeclaration> {
        val directParentDeclarations = ktClassOrObject
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map {
                val name = it
                    .text
                    .substringBefore(" ")
                    .substringBefore("(")
                    .substringBefore("<")

                val fqn =
                    containingFile
                        .imports
                        .firstOrNull { import ->
                            import.name.substringAfterLast(".") == name || import.alias == name
                        }
                        ?.name

                return@map getParentClass(name, fqn)
                    ?: getParentInterface(name, fqn)
                    ?: KoExternalParentDeclarationCore.getInstance(name, it)
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
                val nextParents = if (it as? KoParentProvider != null) {
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

    private fun getParentClass(name: String, fqn: String?): KoClassDeclaration? = DataCore
        .classes
        .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
        ?: containingFile
            .classes()
            .firstOrNull { decl -> decl.name == name }

    private fun getParentInterface(name: String, fqn: String?): KoInterfaceDeclaration? = DataCore
        .interfaces
        .firstOrNull { decl -> (decl.packagee?.fullyQualifiedName + "." + decl.name) == fqn }
        ?: containingFile
            .interfaces()
            .firstOrNull { decl -> decl.name == name }

    override fun numParents(indirectParents: Boolean): Int = parents(indirectParents).size

    override fun countParents(indirectParents: Boolean, predicate: (KoParentDeclaration) -> Boolean): Int =
        parents(indirectParents).count { predicate(it) }

    @Deprecated("Will be removed in v1.0.0.", ReplaceWith("hasParentsWithAllNames(*names)"))
    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> parents().isNotEmpty()
        else -> names.all {
            parents().any { parent -> it == parent.name }
        }
    }

    override fun hasParents(indirectParents: Boolean): Boolean = parents(indirectParents).isNotEmpty()

    override fun hasParentWithName(name: String, vararg names: String, indirectParents: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            parents(indirectParents).any { parent -> it == parent.name }
        }
    }

    override fun hasParentsWithAllNames(name: String, vararg names: String, indirectParents: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            parents(indirectParents).any { parent -> it == parent.name }
        }
    }

    override fun hasParent(indirectParents: Boolean, predicate: (KoParentDeclaration) -> Boolean): Boolean =
        parents(indirectParents).any(predicate)

    override fun hasAllParents(indirectParents: Boolean, predicate: (KoParentDeclaration) -> Boolean): Boolean =
        parents(indirectParents).all(predicate)

    override fun hasParentOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfKClassOf(name, parents(indirectParents)) || names.any { checkIfKClassOf(it, parents(indirectParents)) }

    override fun hasAllParentsOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfKClassOf(name, parents(indirectParents)) && names.all { checkIfKClassOf(it, parents(indirectParents)) }
}
