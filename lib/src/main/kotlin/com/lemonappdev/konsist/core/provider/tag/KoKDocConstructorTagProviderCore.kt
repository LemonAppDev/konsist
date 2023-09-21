package com.lemonappdev.konsist.core.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocConstructorTagProvider
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore

internal interface KoKDocConstructorTagProviderCore :
    KoBaseProviderCore,
    KoKDocConstructorTagProvider,
    KoKDocTagProviderCore {
    override val constructorTag: KoKDocTagDeclaration?
        get() = tags.firstOrNull { it.name == KoKDocTag.CONSTRUCTOR }

    override fun hasConstructorTag(): Boolean = constructorTag != null
}
