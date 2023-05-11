package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoDeclarationCoreProvider {
    fun declarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoNamedDeclaration>

    fun containsDeclarations(
        name: String,
        includeNested: Boolean = false,
    ) = declarations(includeNested).any { it.name == name }
}

interface KoClassCoreProvider : KoDeclarationCoreProvider {
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsClass(
        name: String,
        includeNested: Boolean = false,
    ): Boolean =
        classes(includeNested).any { it.name == name }
}

interface KoInterfaceCoreProvider : KoDeclarationCoreProvider {
    fun interfaces(
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsInterface(
        name: String,
        includeNested: Boolean = false,
    ): Boolean =
        interfaces(includeNested).any { it.name == name }
}

interface KoObjectCoreProvider : KoDeclarationCoreProvider {
    fun objects(
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean = objects(includeNested).any { it.name == name }
}

interface KoPropertyCoreProvider : KoDeclarationCoreProvider {
    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsProperty(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean =
        properties(includeNested, includeLocal).any { it.name == name }
}

interface KoFunctionCoreProvider : KoDeclarationCoreProvider {
    fun functions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsFunction(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean = functions(includeNested, includeLocal).any { it.name == name }
}
