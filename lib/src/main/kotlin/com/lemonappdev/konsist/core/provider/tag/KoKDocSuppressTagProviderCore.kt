package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocSuppressTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocSuppressTagProviderCore :
    KoBaseProviderCore,
    KoKDocSuppressTagProvider,
    KoKDocTagProviderCore {
    override val suppressTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.SUPPRESS }

    override val hasSuppressTag: Boolean
        get() = suppressTag != null
}
