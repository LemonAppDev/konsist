package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocThrowsTagProvider

/**
 * List containing throws tags.
 *
 * @see KoKDocThrowsTagProvider.throwsTags
 */
val <T : KoKDocThrowsTagProvider> List<T>.throwsTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.throwsTags }

/**
 * List containing declarations with throws tag.
 *
 * @return A list containing declarations with throws tag.
 * @see KoKDocThrowsTagProvider.hasThrowsTags
 */
fun <T : KoKDocThrowsTagProvider> List<T>.withThrowsTags(): List<T> = filter { it.hasThrowsTags }

/**
 * List containing declarations with no throws tag.
 *
 * @return A list containing declarations with no throws tag.
 * @see KoKDocThrowsTagProvider.hasThrowsTags
 */
fun <T : KoKDocThrowsTagProvider> List<T>.withoutThrowsTags(): List<T> = filterNot { it.hasThrowsTags }
