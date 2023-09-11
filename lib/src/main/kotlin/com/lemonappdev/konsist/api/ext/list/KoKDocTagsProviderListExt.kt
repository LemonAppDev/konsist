package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocTagsProvider

/**
 * List containing tags.
 */
val <T : KoKDocTagsProvider> List<T>.tags: List<KoKDocTagDeclaration>
    get() = flatMap { it.tags }

/**
 * List containing param tags.
 */
val <T : KoKDocTagsProvider> List<T>.paramTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.paramTags }

/**
 * List containing property tags.
 */
val <T : KoKDocTagsProvider> List<T>.propertyTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.propertyTags }

/**
 * List containing throws tags.
 */
val <T : KoKDocTagsProvider> List<T>.throwsTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.throwsTags }

/**
 * List containing exception tags.
 */
val <T : KoKDocTagsProvider> List<T>.exceptionTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.exceptionTags }

/**
 * List containing sample tags.
 */
val <T : KoKDocTagsProvider> List<T>.sampleTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.sampleTags }

/**
 * List containing see tags.
 */
val <T : KoKDocTagsProvider> List<T>.seeTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.seeTags }

/**
 * List containing author tags.
 */
val <T : KoKDocTagsProvider> List<T>.authorTags: List<KoKDocTagDeclaration>
    get() = flatMap { it.authorTags }
