package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoKDocTagValueProvider

/**
 * Represents a Kotlin documentation tag declaration with a value.
 */
interface KoValuedKDocTagDeclaration :
    KoKDocTagDeclaration,
    KoKDocTagValueProvider,
    KoBaseDeclaration,
    KoBaseProvider
