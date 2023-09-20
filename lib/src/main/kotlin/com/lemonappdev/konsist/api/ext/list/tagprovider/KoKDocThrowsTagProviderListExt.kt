package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocThrowsTagProvider

/**
 * List containing throws tags.
 */
val <T : KoKDocThrowsTagProvider> List<T>.throwsTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.throwsTags }
