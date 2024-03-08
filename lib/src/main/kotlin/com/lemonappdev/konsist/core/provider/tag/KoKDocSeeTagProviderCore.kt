package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSeeTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocSeeTagProviderCore : KoBaseProviderCore, KoKDocSeeTagProvider, KoKDocTagProviderCore {
    override val seeTags: List<KoValuedKDocTagDeclaration>
        get() =
            tags.filter { it.name == KoKDocTag.SEE }
                .map { it as KoValuedKDocTagDeclaration }

    override val numSeeTags: Int
        get() = seeTags.size

    override val hasSeeTags: Boolean
        get() = seeTags.isNotEmpty()
}
