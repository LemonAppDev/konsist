package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoHasDefaultNameProvider

/**
 * Represents a Kotlin companion object declaration.
 */
interface KoCompanionObjectDeclaration :
    KoObjectDeclaration,
    KoHasDefaultNameProvider
