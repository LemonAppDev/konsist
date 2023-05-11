package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoLocalDeclarationProvider {
    fun localDeclarations(): Sequence<KoDeclaration>

    fun containsLocalDeclarations(name: String) = localDeclarations().any { it.name == name }
}

interface KoLocalClassProvider : KoLocalDeclarationProvider {
    fun localClasses(): Sequence<KoClassDeclaration> = localDeclarations().filterIsInstance<KoClassDeclaration>()

    fun containsLocalClass(name: String): Boolean = localClasses().any { it.name == name }
}

interface KoLocalPropertyProvider : KoLocalDeclarationProvider {
    fun localProperties(): Sequence<KoPropertyDeclaration> = localDeclarations().filterIsInstance<KoPropertyDeclaration>()

    fun containsLocalProperty(name: String): Boolean = localProperties().any { it.name == name }
}

interface KoLocalFunctionProvider : KoLocalDeclarationProvider {
    fun localFunctions(): Sequence<KoFunctionDeclaration> = localDeclarations().filterIsInstance<KoFunctionDeclaration>()

    fun containsLocalFunction(name: String): Boolean = localFunctions().any { it.name == name }
}
