package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoDocTag
import com.lemonappdev.konsist.api.declaration.KoDocTagDeclaration

internal open class KoDocTagDeclarationImpl(override val name: KoDocTag, override val description: String) : KoDocTagDeclaration
