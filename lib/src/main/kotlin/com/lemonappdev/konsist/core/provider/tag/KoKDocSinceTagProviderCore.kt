package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSinceTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocSinceTagProviderCore :
    KoBaseProviderCore,
    KoKDocSinceTagProvider,
    KoKDocTagProviderCore {
    override val sinceTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.SINCE }

    override val hasSinceTag: Boolean
        get() = sinceTag != null
}
