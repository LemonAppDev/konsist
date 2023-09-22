package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSeeTagProvider

/**
 * List containing see tags.
 */
val <T : KoKDocSeeTagProvider> List<T>.seeTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.seeTags }

/**
 * List containing declarations with see tag.
 *
 * @return A list containing declarations with see tag.
 */
fun <T : KoKDocSeeTagProvider> List<T>.withSeeTags(): List<T> = filter { it.hasSeeTags }

/**
 * List containing declarations with no see tag.
 *
 * @return A list containing declarations with no see tag.
 */
fun <T : KoKDocSeeTagProvider> List<T>.withoutSeeTags(): List<T> = filterNot { it.hasSeeTags }
