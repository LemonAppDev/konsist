package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocReturnTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocReturnTagProviderCore : KoBaseProviderCore, KoKDocReturnTagProvider, KoKDocTagProviderCore {
    override val returnTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.RETURN }

    override fun hasReturnTag(): Boolean = returnTag != null
}
