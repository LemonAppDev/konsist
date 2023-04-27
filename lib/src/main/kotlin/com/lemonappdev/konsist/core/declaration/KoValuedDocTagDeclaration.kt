package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoTag
open class KoValuedDocTagDeclaration(
    override val name: KoTag,
    val value: String,
    override val description: String,
) : KoDocTagDeclaration(name, description)
