package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocParamTagProvider

/**
 * List containing param tags.
 */
val <T : KoKDocParamTagProvider> List<T>.paramTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.paramTags }

/**
 * List containing declarations with param tag.
 *
 * @return A list containing declarations with param tag.
 */
fun <T : KoKDocParamTagProvider> List<T>.withParamTags(): List<T> = filter { it.hasParamTags }

/**
 * List containing declarations with no param tag.
 *
 * @return A list containing declarations with no param tag.
 */
fun <T : KoKDocParamTagProvider> List<T>.withoutParamTags(): List<T> = filterNot { it.hasParamTags }
