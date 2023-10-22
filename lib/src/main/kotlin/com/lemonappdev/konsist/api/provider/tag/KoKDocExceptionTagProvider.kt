package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc exception tags.
 */
interface KoKDocExceptionTagProvider : KoBaseProvider {
    /**
     * List of `@exception` tags.
     */
    val exceptionTags: List<KoValuedKDocTagDeclaration>

    /**
     * The number of exception tags.
     */
    val numExceptionTags: Int

    /**
     * Determines whatever the declaration has exception tags.
     */
    val hasExceptionTags: Boolean
}
