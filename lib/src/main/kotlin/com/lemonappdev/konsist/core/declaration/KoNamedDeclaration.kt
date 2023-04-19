package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement

/**
 * Declaration with a name
 */
abstract class KoNamedDeclaration(private val ktElement: KtElement) : KoBaseDeclaration(ktElement) {
    /**
     * Name of the declaration
     */
    open val name by lazy { ktElement.name ?: "" }
}
