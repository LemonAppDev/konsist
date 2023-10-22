package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc sample tags.
 */
interface KoKDocSampleTagProvider : KoBaseProvider {
    /**
     * List of `@sample` tags.
     */
    val sampleTags: List<KoValuedKDocTagDeclaration>

    /**
     * The number of sample tags.
     */
    val numSampleTags: Int

    /**
     * Determines whatever the declaration has sample tags.
     */
    val hasSampleTags: Boolean
}
