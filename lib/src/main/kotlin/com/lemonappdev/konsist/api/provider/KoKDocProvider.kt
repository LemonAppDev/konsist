package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its KDoc (documentation).
 */
interface KoKDocProvider : KoBaseProvider {
    /**
     * Documentation of the declaration.
     */
    val kDoc: KoKDocDeclaration?

    /**
     * Determines whatever the declaration has kDoc.
     */
    val hasKDoc: Boolean
}
