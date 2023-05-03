package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag

internal open class KoValuedDocTagDeclarationImpl(
    override val name: KoTag,
    override val value: String,
    override val description: String,
) : KoDocTagDeclarationImpl(name, description), KoValuedDocTagDeclaration
