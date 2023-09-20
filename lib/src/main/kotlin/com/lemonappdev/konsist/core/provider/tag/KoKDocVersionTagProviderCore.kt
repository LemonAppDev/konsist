package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocVersionTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocVersionTagProviderCore : KoBaseProviderCore, KoKDocVersionTagProvider, KoKDocTagsProviderCore {
    override val versionTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.VERSION }
}
