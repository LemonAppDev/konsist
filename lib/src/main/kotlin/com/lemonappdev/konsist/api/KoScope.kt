package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.core.declaration.KoClassDeclaration
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import com.lemonappdev.konsist.core.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.declaration.KoImportDeclaration
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.core.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.core.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclaration

interface KoScope {
    fun files(): Sequence<KoFileDeclaration>
    fun classes(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration>

    fun interfaces(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration>

    fun objects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration>

    fun companionObjects(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoCompanionObjectDeclaration>

    fun functions(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration>

    fun declarations(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
    ): Sequence<KoDeclaration>

    fun properties(
        modifiers: List<KoModifier> = emptyList(),
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration>

    fun imports(): Sequence<KoImportDeclaration>
    fun packages(): Sequence<KoPackageDeclaration>
    fun typeAliases(): Sequence<KoTypeAliasDeclaration>

    operator fun plus(scope: KoScope): KoScope

    operator fun minus(scope: KoScope): KoScope

    operator fun plusAssign(scope: KoScope)

    operator fun minusAssign(scope: KoScope)

    override fun toString(): String

    fun print()
}
