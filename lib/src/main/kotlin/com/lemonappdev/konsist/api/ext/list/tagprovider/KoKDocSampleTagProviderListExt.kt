package com.lemonappdev.konsist.api.ext.list.tagprovider

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSampleTagProvider
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagsProvider

/**
 * List containing sample tags.
 */
val <T : KoKDocSampleTagProvider> List<T>.sampleTags: List<KoValuedKDocTagDeclaration>
    get() = flatMap { it.sampleTags }
