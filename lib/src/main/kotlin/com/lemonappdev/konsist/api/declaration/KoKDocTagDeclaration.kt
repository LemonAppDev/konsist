package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoKDocTagNameAndDescriptionProvider

/**
 * Represents a Kotlin documentation tag declaration.
 */
interface KoKDocTagDeclaration :
    KoKDocTagNameAndDescriptionProvider,
    KoBaseDeclaration,
    KoBaseProvider
