package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
/**
 * Represents a base declaration.
 */
interface KoBaseDeclaration :
    KoPathProvider,
    KoKDocProvider,
    KoLocationProvider,
    KoTextProvider {
    /**
     * File containing the declaration.
     */
    val containingFile: KoFile
}
