package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

interface KoNamedDeclaration : KoBaseDeclaration {
    /**
     * Name of the declaration
     */
    val name: String

    fun hasNameWithPrefix(prefix: String): Boolean

    fun hasNameWithSuffix(suffix: String): Boolean

    fun hasNameContaining(text: String): Boolean

    fun hasNameMatching(regex: Regex): Boolean
}
