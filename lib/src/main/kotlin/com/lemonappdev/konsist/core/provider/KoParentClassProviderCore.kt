package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.util.ParentUtil.checkIfParentOf
import kotlin.reflect.KClass

internal interface KoParentClassProviderCore :
    KoParentClassProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override val parentClass: KoClassDeclaration?
        get() = parentClasses(false).firstOrNull()

    override fun parentClasses(indirectParents: Boolean): List<KoClassDeclaration> =
        parents(indirectParents).filterIsInstance<KoClassDeclaration>()

    override fun numParentClasses(indirectParents: Boolean): Int = parentClasses(indirectParents).size

    override fun countParentClasses(
        indirectParents: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = parentClasses(indirectParents).count { predicate(it) }

    override fun hasParentClass(): Boolean = parentClass != null

    override fun hasParentClass(
        indirectParents: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = parentClasses(indirectParents).any(predicate)

    override fun hasParentClasses(indirectParents: Boolean): Boolean = parentClasses(indirectParents).isNotEmpty()

    override fun hasAllParentClasses(
        indirectParents: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = parentClasses(indirectParents).all(predicate)

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentClass(name: String): Boolean = parentClass?.name == name

    override fun hasParentClassWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasParentClassWithName(listOf(name, *names), indirectParents)

    override fun hasParentClassWithName(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentClasses(indirectParents)
            else -> names.any { parentClasses(indirectParents).any { parentClass -> it == parentClass.name } }
        }

    override fun hasParentClassesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean = hasParentClassesWithAllNames(listOf(name, *names), indirectParents)

    override fun hasParentClassesWithAllNames(
        names: Collection<String>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentClasses(indirectParents)
            else -> names.all { parentClasses(indirectParents).any { parentClass -> it == parentClass.name } }
        }

    override fun hasParentClassOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasParentClassOf(listOf(name, *names), indirectParents)

    override fun hasParentClassOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentClasses(indirectParents)
            else -> names.any { checkIfParentOf(it, parentClasses(indirectParents)) }
        }

    override fun hasAllParentClassesOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean,
    ): Boolean = hasAllParentClassesOf(listOf(name, *names), indirectParents)

    override fun hasAllParentClassesOf(
        names: Collection<KClass<*>>,
        indirectParents: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasParentClasses(indirectParents)
            else -> names.all { checkIfParentOf(it, parentClasses(indirectParents)) }
        }
}
