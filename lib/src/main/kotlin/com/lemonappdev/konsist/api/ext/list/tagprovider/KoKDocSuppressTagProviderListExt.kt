package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocSuppressTagProvider

/**
 * List containing declarations with suppress tag.
 *
 * @return A list containing declarations with suppress tag.
 */
fun <T : KoKDocSuppressTagProvider> List<T>.withSuppressTag(): List<T> = filter { it.hasSuppressTag }

/**
 * List containing declarations with no suppress tag.
 *
 * @return A list containing declarations with no suppress tag.
 */
fun <T : KoKDocSuppressTagProvider> List<T>.withoutSuppressTag(): List<T> = filterNot { it.hasSuppressTag }
