package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin secondary constructor declaration.
 */
interface KoSecondaryConstructorDeclaration :
    KoConstructorDeclaration,
    KoBaseDeclaration,
    KoParentProvider
