package com.lemonappdev.konsist.api.ext.list.provider.tagprovider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocSinceTagProvider

/**
 * List containing declarations with since tag.
 *
 * @return A list containing declarations with since tag.
 */
fun <T : KoKDocSinceTagProvider> List<T>.withSinceTag(): List<T> = filter { it.hasSinceTag }

/**
 * List containing declarations with no since tag.
 *
 * @return A list containing declarations with no since tag.
 */
fun <T : KoKDocSinceTagProvider> List<T>.withoutSinceTag(): List<T> = filterNot { it.hasSinceTag }
