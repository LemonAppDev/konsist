package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocTagNameAndDescriptionProviderCore

internal open class KoKDocTagDeclarationCore(
    override val name: KoKDocTag,
    override val description: String,
    // KDoc containing this tag (e.g. used to check if the tag is suppressed by the documented declaration)
    internal val kDocDeclaration: KoBaseDeclaration,
) : KoKDocTagDeclaration,
    KoKDocTagNameAndDescriptionProviderCore,
    KoBaseProviderCore {
    override fun toString(): String = "${name.type} $description"
}
