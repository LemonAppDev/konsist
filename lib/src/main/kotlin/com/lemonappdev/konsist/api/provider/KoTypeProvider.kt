package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

interface KoTypeProvider : KoBaseProvider {
    /**
     * Type of the declaration.
     */
    val type: KoTypeDeclaration
}
