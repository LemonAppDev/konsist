package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoDeclarationCoreProvider : KoDeclarationProvider {
    fun containsDeclarations(
        name: String,
        includeNested: Boolean = false,
    ) = declarations(includeNested).any { it.name == name }
}

interface KoClassCoreProvider : KoDeclarationCoreProvider, KoClassProvider {
    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoClassDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsClass(
        name: String,
        includeNested: Boolean = false,
    ): Boolean =
        classes(includeNested).any { it.name == name }
}

interface KoInterfaceCoreProvider : KoDeclarationCoreProvider, KoInterfaceProvider {
    override fun interfaces(
        includeNested: Boolean,
    ): Sequence<KoInterfaceDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsInterface(
        name: String,
        includeNested: Boolean = false,
    ): Boolean =
        interfaces(includeNested).any { it.name == name }
}

interface KoObjectCoreProvider : KoDeclarationCoreProvider, KoObjectProvider {
    override fun objects(
        includeNested: Boolean,
    ): Sequence<KoObjectDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean = objects(includeNested).any { it.name == name }
}

interface KoPropertyCoreProvider : KoDeclarationCoreProvider, KoPropertyProvider {
    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoPropertyDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsProperty(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean =
        properties(includeNested, includeLocal).any { it.name == name }
}

interface KoFunctionCoreProvider : KoDeclarationCoreProvider, KoFunctionProvider {
    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoFunctionDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsFunction(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean = functions(includeNested, includeLocal).any { it.name == name }
}
