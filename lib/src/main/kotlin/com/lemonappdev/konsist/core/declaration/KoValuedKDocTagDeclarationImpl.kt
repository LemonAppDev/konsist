package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration

internal open class KoValuedKDocTagDeclarationImpl(
    override val name: KoKDocTag,
    override val value: String,
    override val description: String,
) : KoKDocTagDeclarationImpl(name, description), KoValuedKDocTagDeclaration
