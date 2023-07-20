package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoLocalPropertyProvider : KoBaseProvider {
    fun localProperties(): Sequence<KoPropertyDeclaration>

    fun containsLocalProperty(name: String): Boolean
}
