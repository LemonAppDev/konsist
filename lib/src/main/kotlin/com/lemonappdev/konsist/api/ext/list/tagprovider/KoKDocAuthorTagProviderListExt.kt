package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocAuthorTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider

/**
 * List containing author tags.
 */
val <T : KoKDocAuthorTagProvider> List<T>.authorTags: List<KoKDocTagDeclaration>
    get() = flatMap { it.authorTags }
