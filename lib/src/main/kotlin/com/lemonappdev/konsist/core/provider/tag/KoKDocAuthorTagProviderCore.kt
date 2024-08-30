package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocAuthorTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocAuthorTagProviderCore : KoBaseProviderCore, KoKDocAuthorTagProvider, KoKDocTagProviderCore {
    override val authorTags: List<KoKDocTagDeclaration>
        get() = tags.filter { it.name == KoKDocTag.AUTHOR }

    override val numAuthorTags: Int
        get() = authorTags.size

    override val hasAuthorTags: Boolean
        get() = authorTags.isNotEmpty()
}
