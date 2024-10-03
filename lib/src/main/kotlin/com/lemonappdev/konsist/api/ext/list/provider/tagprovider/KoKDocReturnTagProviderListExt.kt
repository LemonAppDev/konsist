package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocReturnTagProvider

/**
 * List containing declarations with return tag.
 *
 * @return A list containing declarations with return tag.
 */
fun <T : KoKDocReturnTagProvider> List<T>.withReturnTag(): List<T> = filter { it.hasReturnTag }

/**
 * List containing declarations with no return tag.
 *
 * @return A list containing declarations with no return tag.
 */
fun <T : KoKDocReturnTagProvider> List<T>.withoutReturnTag(): List<T> = filterNot { it.hasReturnTag }
