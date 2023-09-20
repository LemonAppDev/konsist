package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocExceptionTagProvider

/**
 * List containing exception tags.
 */
val <T : KoKDocExceptionTagProvider> List<T>.exceptionTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.exceptionTags }
