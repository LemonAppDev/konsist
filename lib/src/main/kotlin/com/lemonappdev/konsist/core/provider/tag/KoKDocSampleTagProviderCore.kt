package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSampleTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocSampleTagProviderCore :
    KoBaseProviderCore,
    KoKDocSampleTagProvider,
    KoKDocTagProviderCore {
    override val sampleTags: List<KoValuedKDocTagDeclaration>
        get() =
            tags
                .filter { it.name == KoKDocTag.SAMPLE }
                .map { it as KoValuedKDocTagDeclaration }

    override val numSampleTags: Int
        get() = sampleTags.size

    override val hasSampleTags: Boolean
        get() = sampleTags.isNotEmpty()
}
