package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSinceTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider

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
