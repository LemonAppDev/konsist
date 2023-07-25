package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoKDocDescriptionProvider
import com.lemonappdev.konsist.api.provider.KoKDocTagsProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin declaration.
 */
interface KoKDocDeclaration :
    KoKDocDescriptionProvider,
    KoKDocTagsProvider,
    KoTextProvider
