package com.lemonappdev.konsist.core.declaration

open class KoValuedDocTag(
    override val name: String,
    val value: String,
    override val description: String
) : KoDocTag(name, description)
