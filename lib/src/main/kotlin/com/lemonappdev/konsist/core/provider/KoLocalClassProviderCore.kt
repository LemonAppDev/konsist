package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider

internal interface KoLocalClassProviderCore : KoLocalClassProvider, KoLocalDeclarationProviderCore, KoBaseProviderCore {
    override fun localClasses(): Sequence<KoClassDeclaration> = localDeclarations().filterIsInstance<KoClassDeclaration>()

    override fun containsLocalClass(name: String): Boolean = localClasses().any { it.name == name }
}
