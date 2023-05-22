package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import org.jetbrains.kotlin.psi.KtElement

internal abstract class KoNamedDeclarationImpl(private val ktElement: KtElement) : KoBaseDeclarationImpl(ktElement), KoNamedDeclaration {
    override val name: String by lazy { ktElement.name ?: "" }

    override fun hasNameStartingWith(prefix: String): Boolean = name.startsWith(prefix)

    override fun hasNameEndingWith(suffix: String): Boolean = name.endsWith(suffix)

    override fun hasNameContaining(text: String): Boolean = name.contains(text)

    override fun hasNameMatching(regex: Regex): Boolean = name.matches(regex)
}
