package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertySetterTagProvider

/**
 * List containing declarations with property setter tag.
 *
 * @return A list containing declarations with property setter tag.
 */
fun <T : KoKDocPropertySetterTagProvider> List<T>.withPropertySetterTag(): List<T> = filter { it.hasPropertySetterTag }

/**
 * List containing declarations with no property setter tag.
 *
 * @return A list containing declarations with no property setter tag.
 */
fun <T : KoKDocPropertySetterTagProvider> List<T>.withoutPropertySetterTag(): List<T> = filterNot { it.hasPropertySetterTag }
