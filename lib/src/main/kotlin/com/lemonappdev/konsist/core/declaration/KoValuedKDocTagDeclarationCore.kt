package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagValueProviderCore

internal open class KoValuedKDocTagDeclarationCore(
    final override val name: KoKDocTag,
    final override val value: String,
    final override val description: String,
) : KoKDocTagDeclarationCore(name, description),
    KoValuedKDocTagDeclaration,
    KoKDocTagValueProviderCore,
    KoBaseProviderCore {
    final override fun toString(): String = name.type + " " + value + " " + description
}
