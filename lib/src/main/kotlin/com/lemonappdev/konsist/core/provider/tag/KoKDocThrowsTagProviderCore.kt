package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocThrowsTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocThrowsTagProviderCore : KoBaseProviderCore, KoKDocThrowsTagProvider, KoKDocTagProviderCore {
    override val throwsTags: List<KoValuedKDocTagDeclaration>
        get() =
            tags.filter { it.name == KoKDocTag.THROWS }
                .map { it as KoValuedKDocTagDeclaration }

    override val numThrowsTags: Int
        get() = throwsTags.size

    override val hasThrowsTags: Boolean
        get() = throwsTags.isNotEmpty()
}
