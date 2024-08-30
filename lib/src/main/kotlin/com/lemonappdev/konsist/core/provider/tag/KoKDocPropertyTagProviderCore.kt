package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocPropertyTagProviderCore :
    KoBaseProviderCore,
    KoKDocPropertyTagProvider,
    KoKDocTagProviderCore {
    override val propertyTags: List<KoValuedKDocTagDeclaration>
        get() =
            tags
                .filter { it.name == KoKDocTag.PROPERTY }
                .map { it as KoValuedKDocTagDeclaration }

    override val numPropertyTags: Int
        get() = propertyTags.size

    override val hasPropertyTags: Boolean
        get() = propertyTags.isNotEmpty()
}
