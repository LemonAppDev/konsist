package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration

internal open class KoKDocTagDeclarationImpl(override val name: KoKDocTag, override val description: String) : KoKDocTagDeclaration
