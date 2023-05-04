package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoDocTag

interface KoDocTagDeclaration {
    val name: KoDocTag
    val description: String
}
