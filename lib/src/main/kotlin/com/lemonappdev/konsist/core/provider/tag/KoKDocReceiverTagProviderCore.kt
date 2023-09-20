package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocReceiverTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocReceiverTagProviderCore: KoBaseProviderCore, KoKDocReceiverTagProvider,
    KoKDocTagsProviderCore {
    override val receiverTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.RECEIVER }
}
