package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocThrowsTagProvider

/**
 * List containing throws tags.
 */
val <T : KoKDocThrowsTagProvider> List<T>.throwsTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.throwsTags }

/**
 * List containing declarations with throws tag.
 *
 * @return A list containing declarations with throws tag.
 */
fun <T : KoKDocThrowsTagProvider> List<T>.withThrowsTags(): List<T> = filter { it.hasThrowsTags }

/**
 * List containing declarations with no throws tag.
 *
 * @return A list containing declarations with no throws tag.
 */
fun <T : KoKDocThrowsTagProvider> List<T>.withoutThrowsTags(): List<T> = filterNot { it.hasThrowsTags }
