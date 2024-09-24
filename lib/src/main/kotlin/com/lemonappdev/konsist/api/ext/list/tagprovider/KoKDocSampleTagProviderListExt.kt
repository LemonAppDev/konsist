package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSampleTagProvider

/**
 * List containing sample tags.
 */
val <T : KoKDocSampleTagProvider> List<T>.sampleTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.sampleTags }

/**
 * List containing declarations with sample tag.
 *
 * @return A list containing declarations with sample tag.
 */
fun <T : KoKDocSampleTagProvider> List<T>.withSampleTags(): List<T> = filter { it.hasSampleTags }

/**
 * List containing declarations with no sample tag.
 *
 * @return A list containing declarations with no sample tag.
 */
fun <T : KoKDocSampleTagProvider> List<T>.withoutSampleTags(): List<T> = filterNot { it.hasSampleTags }
