package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoDeclarationProvider {
    fun declarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoBaseDeclaration>
}

interface KoClassProvider : KoDeclarationProvider {
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration>
}

interface KoInterfaceProvider : KoDeclarationProvider {
    fun interfaces(
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration>
}

interface KoObjectProvider : KoDeclarationProvider {
    fun objects(
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration>
}

interface KoPropertyProvider : KoDeclarationProvider {
    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration>
}

interface KoFunctionProvider : KoDeclarationProvider {
    fun functions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration>
}
