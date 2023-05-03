package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag
open class KoValuedDocTagDeclaration(
    override val name: KoTag,
    val value: String,
    override val description: String,
) : KoDocTagDeclaration(name, description)
