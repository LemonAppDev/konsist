package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.provider.tag.KoKDocConstructorTagProvider

/**
 * List containing declarations with constructor tag.
 *
 * @return A list containing declarations with constructor tag.
 */
fun <T : KoKDocConstructorTagProvider> List<T>.withConstructorTag(): List<T> = filter { it.hasConstructorTag }

/**
 * List containing declarations with no constructor tag.
 *
 * @return A list containing declarations with no constructor tag.
 */
fun <T : KoKDocConstructorTagProvider> List<T>.withoutConstructorTag(): List<T> = filterNot { it.hasConstructorTag }
