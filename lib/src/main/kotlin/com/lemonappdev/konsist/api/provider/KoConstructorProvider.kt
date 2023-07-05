package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration

interface KoConstructorProvider: KoParentProvider {
    /**
     * The primary constructor of the declaration.
     */
    val primaryConstructor: KoPrimaryConstructorDeclaration?

    /**
     * The secondary constructors of the declaration.
     */
    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: List<KoConstructorDeclaration>
}
