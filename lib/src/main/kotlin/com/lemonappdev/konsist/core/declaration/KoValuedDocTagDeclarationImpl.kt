package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedDocTagDeclaration

internal open class KoValuedDocTagDeclarationImpl(
    override val name: KoDocTag,
    override val value: String,
    override val description: String,
) : KoDocTagDeclarationImpl(name, description), KoValuedDocTagDeclaration
