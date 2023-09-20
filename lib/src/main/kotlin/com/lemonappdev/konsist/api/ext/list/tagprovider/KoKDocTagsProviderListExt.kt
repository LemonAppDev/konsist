package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider

/**
 * List containing tags.
 */
val <T : KoKDocTagsProvider> List<T>.tags: List<KoKDocTagDeclaration>
    get() = flatMap { it.tags }
