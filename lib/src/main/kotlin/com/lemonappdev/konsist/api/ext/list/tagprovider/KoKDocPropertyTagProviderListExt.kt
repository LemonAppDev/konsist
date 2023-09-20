package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider

/**
 * List containing property tags.
 */
val <T : KoKDocPropertyTagProvider> List<T>.propertyTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.propertyTags }
