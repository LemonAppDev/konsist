package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagValueProviderCore

internal open class KoValuedKDocTagDeclarationCore(
    final override val name: KoKDocTag,
    final override val value: String,
    final override val description: String,
    kDocDeclaration: KoBaseDeclaration,
) : KoKDocTagDeclarationCore(name, description, kDocDeclaration),
    KoValuedKDocTagDeclaration,
    KoKDocTagValueProviderCore,
    KoBaseProviderCore {
    final override fun toString(): String = "${name.type} $value $description"
}
