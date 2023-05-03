package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag
open class KoValuedDocTagDeclarationImpl(
    override val name: KoTag,
    val value: String,
    override val description: String,
) : KoDocTagDeclarationImpl(name, description)
