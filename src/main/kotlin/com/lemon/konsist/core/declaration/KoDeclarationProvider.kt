package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.util.KoDeclarationUtil

interface KoDeclarationProvider {
    fun classes(includeNested: Boolean = false): List<KoClass> =
        KoDeclarationUtil.getKoDeclarations(declarations(), includeNested)

    fun containsClass(name: String, modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false): Boolean =
        classes(includeNested).any { it.name == name && it.hasModifiers(*modifiers.toTypedArray()) }

    fun interfaces(includeNested: Boolean = false): List<KoInterface> =
        KoDeclarationUtil.getKoDeclarations(declarations(), includeNested)

    fun containsInterface(name: String, modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false): Boolean =
        interfaces(includeNested).any { it.name == name && it.hasModifiers(*modifiers.toTypedArray()) }

    fun objects(includeNested: Boolean = false): List<KoObject> =
        KoDeclarationUtil.getKoDeclarations(declarations(), includeNested)

    fun containsObject(name: String, modifiers: List<KoModifier> = emptyList(), includeNested: Boolean = false): Boolean =
        objects(includeNested).any { it.name == name && it.hasModifiers(*modifiers.toTypedArray()) }

    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoProperty> = KoDeclarationUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsProperty(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean =
        properties(includeNested, includeLocal).any { it.name == name && it.hasModifiers(*modifiers.toTypedArray()) }

    fun functions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoFunction> = KoDeclarationUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    fun containsFunction(
        name: String,
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean = functions(includeNested, includeLocal).any { it.name == name && it.hasModifiers(*modifiers.toTypedArray()) }

    fun declarations(includeNested: Boolean = false, includeLocal: Boolean = false): List<KoDeclaration>

    fun containsDeclarations(name: String, includeNested: Boolean = false) = declarations(includeNested).any { it.name == name }
}
