package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement

/**
 * Declaration with a name
 */
abstract class KoNamedDeclarationImpl(private val ktElement: KtElement) : KoBaseDeclarationImpl(ktElement) {
    /**
     * Name of the declaration
     */
    open val name by lazy { ktElement.name ?: "" }

    fun hasNameWithPrefix(prefix: String) = name.startsWith(prefix)

    fun hasNameWithSuffix(suffix: String) = name.endsWith(suffix)

    fun hasNameContaining(text: String) = name.contains(text)

    fun hasNameMatching(regex: Regex) = name.matches(regex)
}
