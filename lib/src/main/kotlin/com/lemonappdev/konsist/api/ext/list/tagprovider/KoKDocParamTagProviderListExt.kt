package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocParamTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider


/**
 * List containing param tags.
 */
val <T : KoKDocParamTagProvider> List<T>.paramTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.paramTags }
