package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocPropertySetterTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocPropertySetterTagProviderCore :
    KoBaseProviderCore,
    KoKDocPropertySetterTagProvider,
    KoKDocTagProviderCore {
    override val propertySetterTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.PROPERTY_SETTER }

    override fun hasPropertySetterTag(): Boolean = propertySetterTag != null
}
