package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.const.KoTag
open class KoValuedDocTag(
    override val name: KoTag,
    val value: String,
    override val description: String,
) : KoDocTag(name, description)
