package com.lemon.konsist.core.declaration.provider

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.declaration.KoClass
import com.lemon.konsist.core.declaration.KoCompanionObject
import com.lemon.konsist.core.declaration.KoDeclaration
import com.lemon.konsist.core.declaration.KoFunction
import com.lemon.konsist.core.declaration.KoInterface
import com.lemon.konsist.core.declaration.KoObject
import com.lemon.konsist.core.declaration.KoProperty

interface KoDeclarationProvider {
    fun declarations(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoDeclaration>

    fun containsDeclarations(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ) = declarations(modifiers, includeNested).any { it.name == name && it.hasKoModifiers(*modifiers.toTypedArray()) }
}

interface KoClassProvider : KoDeclarationProvider {
    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoClass> =
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
    ): List<KoInterface> =
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
    ): List<KoObject> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested)

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
    ): List<KoCompanionObject> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested)

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
    ): List<KoProperty> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested, includeLocal)

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
    ): List<KoFunction> = KoDeclarationProviderUtil.getKoDeclarations(declarations(), modifiers, includeNested, includeLocal)

    fun containsFunction(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean = functions(modifiers, includeNested, includeLocal).any { it.name == name }
}
