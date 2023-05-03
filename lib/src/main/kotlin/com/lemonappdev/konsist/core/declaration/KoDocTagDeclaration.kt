package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoTag

interface KoDocTagDeclaration {
    val name: KoTag
    val description: String
}
