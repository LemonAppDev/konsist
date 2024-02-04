package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.util.KClassUtil.checkIfKClassOf
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

    override fun countParentClasses(indirectParents: Boolean, predicate: (KoClassDeclaration) -> Boolean): Int =
        parentClasses(indirectParents).count { predicate(it) }

    override fun hasParentClass(): Boolean = parentClass != null

    override fun hasParentClass(indirectParents: Boolean, predicate: (KoClassDeclaration) -> Boolean): Boolean =
        parentClasses(indirectParents).any(predicate)

    override fun hasParentClasses(indirectParents: Boolean): Boolean = parentClasses(indirectParents).isNotEmpty()

    override fun hasAllParentClasses(indirectParents: Boolean, predicate: (KoClassDeclaration) -> Boolean): Boolean =
        parentClasses(indirectParents).all(predicate)

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentClass(name: String): Boolean = parentClass?.name == name

    override fun hasParentClassWithName(name: String, vararg names: String, indirectParents: Boolean): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any { parentClasses(indirectParents).any { parentClass -> it == parentClass.name } }
    }

    override fun hasParentClassesWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            parentClasses(indirectParents).any { parentClass -> it == parentClass.name }
        }
    }

    override fun hasParentClassOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfKClassOf(name, parentClasses(indirectParents)) ||
            names.any { checkIfKClassOf(it, parentClasses(indirectParents)) }

    override fun hasAllParentClassesOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean): Boolean =
        checkIfKClassOf(name, parentClasses(indirectParents)) &&
            names.all { checkIfKClassOf(it, parentClasses(indirectParents)) }
}
