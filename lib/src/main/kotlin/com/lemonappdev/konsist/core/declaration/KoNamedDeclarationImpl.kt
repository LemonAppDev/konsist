package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement

/**
 * Declaration with a name
 */
internal abstract class KoNamedDeclarationImpl(private val ktElement: KtElement) : KoBaseDeclarationImpl(ktElement), KoNamedDeclaration {
    /**
     * Name of the declaration
     */
    override val name by lazy { ktElement.name ?: "" }

    override fun hasNameWithPrefix(prefix: String) = name.startsWith(prefix)

    override fun hasNameWithSuffix(suffix: String) = name.endsWith(suffix)

    override fun hasNameContaining(text: String) = name.contains(text)

    override fun hasNameMatching(regex: Regex) = name.matches(regex)
}
