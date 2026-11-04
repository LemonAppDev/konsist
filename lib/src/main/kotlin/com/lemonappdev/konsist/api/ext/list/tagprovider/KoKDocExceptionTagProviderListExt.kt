package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocExceptionTagProvider

/**
 * List containing exception tags.
 *
 * @see KoKDocExceptionTagProvider.exceptionTags
 */
val <T : KoKDocExceptionTagProvider> List<T>.exceptionTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.exceptionTags }

/**
 * List containing declarations with exception tag.
 *
 * @return A list containing declarations with exception tag.
 * @see KoKDocExceptionTagProvider.hasExceptionTags
 */
fun <T : KoKDocExceptionTagProvider> List<T>.withExceptionTags(): List<T> = filter { it.hasExceptionTags }

/**
 * List containing declarations with no exception tag.
 *
 * @return A list containing declarations with no exception tag.
 * @see KoKDocExceptionTagProvider.hasExceptionTags
 */
fun <T : KoKDocExceptionTagProvider> List<T>.withoutExceptionTags(): List<T> = filterNot { it.hasExceptionTags }
