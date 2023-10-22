package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its primary constructor.
 */
interface KoPrimaryConstructorProvider : KoBaseProvider {
    /**
     * The parent interfaces of the declaration.
     */
    val primaryConstructor: KoPrimaryConstructorDeclaration?

    /**
     * Determines whatever declaration has primary constructor.
     */
    val hasPrimaryConstructor: Boolean
}
