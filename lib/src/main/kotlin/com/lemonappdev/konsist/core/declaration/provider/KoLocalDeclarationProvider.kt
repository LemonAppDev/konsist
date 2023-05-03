package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl

interface KoLocalDeclarationProvider {
    fun localDeclarations(): Sequence<KoDeclarationImpl>

    fun containsLocalDeclarations(name: String) = localDeclarations().any { it.name == name }
}

interface KoLocalClassProvider : KoLocalDeclarationProvider {
    fun localClasses(): Sequence<KoClassDeclarationImpl> = localDeclarations().filterIsInstance<KoClassDeclarationImpl>()

    fun containsLocalClass(name: String): Boolean = localClasses().any { it.name == name }
}

interface KoLocalPropertyProvider : KoLocalDeclarationProvider {
    fun localProperties(): Sequence<KoPropertyDeclarationImpl> = localDeclarations().filterIsInstance<KoPropertyDeclarationImpl>()

    fun containsLocalProperty(name: String): Boolean = localProperties().any { it.name == name }
}

interface KoLocalFunctionProvider : KoLocalDeclarationProvider {
    fun localFunctions(): Sequence<KoFunctionDeclarationImpl> = localDeclarations().filterIsInstance<KoFunctionDeclarationImpl>()

    fun containsLocalFunction(name: String): Boolean = localFunctions().any { it.name == name }
}
