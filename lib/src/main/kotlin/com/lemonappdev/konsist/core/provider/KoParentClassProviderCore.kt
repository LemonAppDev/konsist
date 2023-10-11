package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass

internal interface KoParentClassProviderCore :
    KoParentClassProvider,
    KoBaseProviderCore,
    KoParentProviderCore {
    override val parentClass: KoClassDeclaration?
        get() = parents.firstOrNull { it is KoClassDeclaration } as? KoClassDeclaration

    override fun hasParentClass(predicate: ((KoClassDeclaration) -> Boolean)?): Boolean = when (predicate) {
        null -> parentClass != null
        else -> parentClass?.let { predicate(it) } ?: false
    }

    override fun hasParentClassWithName(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any { parentClass?.name == it }
    }

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentClass(name: String): Boolean = parentClass?.name == name

    override fun hasParentClassOf(name: KClass<*>, vararg names: KClass<*>): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any { checkIfParentClassOf(it) }
    }

    private fun checkIfParentClassOf(kClass: KClass<*>): Boolean =
        parentClass?.name == kClass.simpleName || parentClass?.fullyQualifiedName == kClass.qualifiedName
}
