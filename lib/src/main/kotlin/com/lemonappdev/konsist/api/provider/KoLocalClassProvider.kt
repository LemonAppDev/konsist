package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil.localDeclarations

interface KoLocalClassProvider {
    fun localClasses(): Sequence<KoClassDeclaration>

    fun containsLocalClass(name: String): Boolean
}
