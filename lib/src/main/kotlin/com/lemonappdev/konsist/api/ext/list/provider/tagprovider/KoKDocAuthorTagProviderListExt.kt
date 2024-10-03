package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocAuthorTagProvider

/**
 * List containing author tags.
 */
val <T : KoKDocAuthorTagProvider> List<T>.authorTags: List<KoKDocTagDeclaration>
    get() = flatMap { it.authorTags }

/**
 * List containing declarations with author tag.
 *
 * @return A list containing declarations with author tag.
 */
fun <T : KoKDocAuthorTagProvider> List<T>.withAuthorTags(): List<T> = filter { it.hasAuthorTags }

/**
 * List containing declarations with no author tag.
 *
 * @return A list containing declarations with no author tag.
 */
fun <T : KoKDocAuthorTagProvider> List<T>.withoutAuthorTags(): List<T> = filterNot { it.hasAuthorTags }
