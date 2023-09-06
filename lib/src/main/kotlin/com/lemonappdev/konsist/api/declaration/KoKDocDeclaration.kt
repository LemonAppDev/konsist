package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoKDocDescriptionProvider
import com.lemonappdev.konsist.api.provider.KoKDocTagsProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin KDoc declaration.
 */
interface KoKDocDeclaration :
    KoBaseDeclaration,
    KoKDocDescriptionProvider,
    KoKDocTagsProvider,
    KoTextProvider,
    KoLocationProvider,
    KoPathProvider {
    /**
     * String representing the kDoc.
     *
     * @return a string representing the kDoc.
     */
    override fun toString(): String
}
