package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocExceptionTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocExceptionTagProviderCore :
    KoBaseProviderCore,
    KoKDocExceptionTagProvider,
    KoKDocTagProviderCore {
    override val exceptionTags: List<KoValuedKDocTagDeclaration>
        get() =
            tags.filter { it.name == KoKDocTag.EXCEPTION }
                .map { it as KoValuedKDocTagDeclaration }

    override val numExceptionTags: Int
        get() = exceptionTags.size

    override val hasExceptionTags: Boolean
        get() = exceptionTags.isNotEmpty()
}
