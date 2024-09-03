package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoKDocProvider

/**
 * Represents a Kotlin secondary constructor declaration.
 */
interface KoSecondaryConstructorDeclaration :
    KoConstructorDeclaration,
    KoKDocProvider
