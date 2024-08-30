package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocParamTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocParamTagProviderCore :
    KoBaseProviderCore,
    KoKDocParamTagProvider,
    KoKDocTagProviderCore {
    override val paramTags: List<KoValuedKDocTagDeclaration>
        get() =
            tags
                .filter { it.name == KoKDocTag.PARAM }
                .map { it as KoValuedKDocTagDeclaration }

    override val numParamTags: Int
        get() = paramTags.size

    override val hasParamTags: Boolean
        get() = paramTags.isNotEmpty()
}
