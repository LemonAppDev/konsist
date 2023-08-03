package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration

internal open class KoValuedKDocTagDeclarationCore(
    override val name: KoKDocTag,
    override val value: String,
    override val description: String,
) : KoKDocTagDeclarationCore(name, description), KoValuedKDocTagDeclaration
