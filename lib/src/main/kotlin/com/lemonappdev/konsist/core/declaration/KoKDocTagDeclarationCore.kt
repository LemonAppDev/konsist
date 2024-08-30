package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagNameAndDescriptionProviderCore

internal open class KoKDocTagDeclarationCore(override val name: KoKDocTag, override val description: String) :
    KoKDocTagDeclaration,
    KoKDocTagNameAndDescriptionProviderCore,
    KoBaseProviderCore {
    override fun toString(): String = name.type + " " + description
}
