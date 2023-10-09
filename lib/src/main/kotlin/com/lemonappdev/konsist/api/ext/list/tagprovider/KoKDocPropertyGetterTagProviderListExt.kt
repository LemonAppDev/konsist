package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyGetterTagProvider

/**
 * List containing declarations with property getter tag.
 *
 * @return A list containing declarations with property getter tag.
 */
fun <T : KoKDocPropertyGetterTagProvider> List<T>.withPropertyGetterTag(): List<T> = filter { it.hasPropertyGetterTag }

/**
 * List containing declarations with no property getter tag.
 *
 * @return A list containing declarations with no property getter tag.
 */
fun <T : KoKDocPropertyGetterTagProvider> List<T>.withoutPropertyGetterTag(): List<T> = filterNot { it.hasPropertyGetterTag }
