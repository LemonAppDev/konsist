package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider

/**
 * List containing tags.
 */
val <T : KoKDocTagProvider> List<T>.tags: List<KoKDocTagDeclaration>
    get() = flatMap { it.tags }
