package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoTag

interface KoDocTagDeclaration {
    val name: KoTag
    val description: String
}
