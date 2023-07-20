package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

interface KoLocalClassProvider : KoBaseProvider {
    fun localClasses(): Sequence<KoClassDeclaration>

    fun containsLocalClass(name: String): Boolean
}
