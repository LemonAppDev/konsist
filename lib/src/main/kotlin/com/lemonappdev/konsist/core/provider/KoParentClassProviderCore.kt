package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider

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

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasParents()"))
    override fun hasParentClass(name: String?): Boolean = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }
}
