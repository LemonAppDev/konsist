package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

interface KoTypeProvider {
    /**
     * Type of the declaration.
     */
    val type: KoTypeDeclaration
}
