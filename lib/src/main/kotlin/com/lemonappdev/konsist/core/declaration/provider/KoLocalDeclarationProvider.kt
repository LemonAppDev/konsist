package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.core.declaration.KoClass
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import com.lemonappdev.konsist.core.declaration.KoFunction
import com.lemonappdev.konsist.core.declaration.KoProperty

interface KoLocalDeclarationProvider {
    fun localDeclarations(): List<KoDeclaration>

    fun containsLocalDeclarations(name: String) = localDeclarations().any { it.name == name }
}

interface KoLocalClassProvider : KoLocalDeclarationProvider {
    fun localClasses(): List<KoClass> = localDeclarations().filterIsInstance<KoClass>()

    fun containsLocalClass(name: String): Boolean = localClasses().any { it.name == name }
}

interface KoLocalPropertyProvider : KoLocalDeclarationProvider {
    fun localProperties(): List<KoProperty> = localDeclarations().filterIsInstance<KoProperty>()

    fun containsLocalProperty(name: String): Boolean = localProperties().any { it.name == name }
}

interface KoLocalFunctionProvider : KoLocalDeclarationProvider {
    fun localFunctions(): List<KoFunction> = localDeclarations().filterIsInstance<KoFunction>()

    fun containsLocalFunction(name: String): Boolean = localFunctions().any { it.name == name }
}
