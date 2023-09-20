package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSeeTagProvider

/**
 * List containing see tags.
 */
val <T : KoKDocSeeTagProvider> List<T>.seeTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.seeTags }
