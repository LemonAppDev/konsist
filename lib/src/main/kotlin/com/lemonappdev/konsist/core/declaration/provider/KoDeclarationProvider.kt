package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoDeclarationProvider {
    fun declarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoNamedDeclaration>

    fun containsNamedDeclarations(
        name: String,
        includeNested: Boolean = false,
    ) = declarations(includeNested).any { it.name == name }
}

interface KoClassProvider : KoDeclarationProvider {
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration> =
        KoDeclarationProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsClass(
        name: String,
        includeNested: Boolean = false,
    ): Boolean =
        classes(includeNested).any { it.name == name }
}

interface KoInterfaceProvider : KoDeclarationProvider {
    fun interfaces(
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration> =
        KoDeclarationProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsInterface(
        name: String,
        includeNested: Boolean = false,
    ): Boolean =
        interfaces(includeNested).any { it.name == name }
}

interface KoObjectProvider : KoDeclarationProvider {
    fun objects(
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean = objects(includeNested).any { it.name == name }
}

interface KoCompanionObjectProvider : KoDeclarationProvider {
    fun companionObjects(
        includeNested: Boolean = false,
    ): Sequence<KoCompanionObjectDeclaration> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), includeNested)

    fun containsCompanionObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean = companionObjects(includeNested).any { it.name == name }
}

interface KoPropertyProvider : KoDeclarationProvider {
    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsProperty(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean =
        properties(includeNested, includeLocal).any { it.name == name }
}

interface KoFunctionProvider : KoDeclarationProvider {
    fun functions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsFunction(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean = functions(includeNested, includeLocal).any { it.name == name }
}
