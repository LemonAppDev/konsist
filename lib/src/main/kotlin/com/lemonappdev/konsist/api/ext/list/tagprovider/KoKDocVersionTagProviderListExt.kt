package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSinceTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocVersionTagProvider

/**
 * List containing declarations with version tag.
 *
 * @return A list containing declarations with version tag.
 */
fun <T : KoKDocVersionTagProvider> List<T>.withVersionTag(): List<T> = filter { it.hasVersionTag }

/**
 * List containing declarations with no version tag.
 *
 * @return A list containing declarations with no version tag.
 */
fun <T : KoKDocVersionTagProvider> List<T>.withoutVersionTag(): List<T> = filterNot { it.hasVersionTag }
