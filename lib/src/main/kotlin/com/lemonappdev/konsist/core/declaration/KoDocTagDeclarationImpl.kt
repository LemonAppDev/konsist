package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag
import com.lemonappdev.konsist.api.declaration.KoDocTagDeclaration

internal open class KoDocTagDeclarationImpl(override val name: KoTag, override val description: String) : KoDocTagDeclaration
