package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin constructor declaration.
 */
interface KoConstructorDeclaration :
    KoParametrizedDeclaration,
    KoBaseDeclaration,
    KoParentProvider
