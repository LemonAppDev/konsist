package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoLocalPropertyProvider : KoProvider {
    fun localProperties(): Sequence<KoPropertyDeclaration>

    fun containsLocalProperty(name: String): Boolean
}
