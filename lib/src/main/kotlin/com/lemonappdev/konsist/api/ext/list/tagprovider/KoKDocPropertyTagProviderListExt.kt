package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyTagProvider

/**
 * List containing property tags.
 */
val <T : KoKDocPropertyTagProvider> List<T>.propertyTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.propertyTags }

/**
 * List containing declarations with property tag.
 *
 * @return A list containing declarations with property tag.
 */
fun <T : KoKDocPropertyTagProvider> List<T>.withPropertyTags(): List<T> = filter { it.hasPropertyTags }

/**
 * List containing declarations with no property tag.
 *
 * @return A list containing declarations with no property tag.
 */
fun <T : KoKDocPropertyTagProvider> List<T>.withoutPropertyTags(): List<T> = filterNot { it.hasPropertyTags }
