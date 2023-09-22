package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertyGetterTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocPropertyGetterTagProviderCore :
    KoBaseProviderCore,
    KoKDocPropertyGetterTagProvider,
    KoKDocTagProviderCore {
    override val propertyGetterTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.PROPERTY_GETTER }

    override val hasPropertyGetterTag: Boolean
        get() = propertyGetterTag != null
}
