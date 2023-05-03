package com.lemonappdev.konsist.core.declaration.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl

interface KoDeclarationProvider {
    fun declarations(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoDeclarationImpl>

    fun containsDeclarations(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ) = declarations(modifiers, includeNested).any { it.name == name && it.hasModifiers(*modifiers.toTypedArray()) }
}

interface KoClassProvider : KoDeclarationProvider {
    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclarationImpl> =
        KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested, includeLocal)

    fun containsClass(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Boolean =
        classes(modifiers, includeNested).any { it.name == name }
}

interface KoInterfaceProvider : KoDeclarationProvider {
    fun interfaces(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclarationImpl> =
        KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested)

    fun containsInterface(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Boolean =
        interfaces(modifiers, includeNested).any { it.name == name }
}

interface KoObjectProvider : KoDeclarationProvider {
    fun objects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclarationImpl> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested)

    fun containsObject(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Boolean = objects(modifiers, includeNested).any { it.name == name }
}

interface KoCompanionObjectProvider : KoDeclarationProvider {
    fun companionObjects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoCompanionObjectDeclarationImpl> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested)

    fun containsCompanionObject(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Boolean = companionObjects(modifiers, includeNested).any { it.name == name }
}

interface KoPropertyProvider : KoDeclarationProvider {
    fun properties(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclarationImpl> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested, includeLocal)

    fun containsProperty(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean =
        properties(modifiers, includeNested, includeLocal).any { it.name == name }
}

interface KoFunctionProvider : KoDeclarationProvider {
    fun functions(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclarationImpl> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested, includeLocal)

    fun containsFunction(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean = functions(modifiers, includeNested, includeLocal).any { it.name == name }
}
