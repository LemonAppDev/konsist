package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin primary constructor declaration.
 */
interface KoPrimaryConstructorDeclaration :
    KoConstructorDeclaration,
    KoBaseDeclaration,
    KoParentProvider
