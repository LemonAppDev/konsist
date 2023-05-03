package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoInterfaceDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl

@Suppress("detekt.TooManyFunctions")
internal class KoScopeImpl(
    private var koFiles: Sequence<KoFileDeclarationImpl>,
) : KoScope {
    constructor(koFileDeclaration: KoFileDeclarationImpl) : this(sequenceOf(koFileDeclaration))

    override fun files(): Sequence<KoFileDeclarationImpl> = koFiles.sortedBy { it.filePath }

    override fun classes(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoClassDeclarationImpl> =
        koFiles.flatMap { it.classes(modifiers, includeNested, includeLocal) }

    override fun interfaces(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
    ): Sequence<KoInterfaceDeclarationImpl> =
        koFiles.flatMap { it.interfaces(modifiers, includeNested) }

    override fun objects(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
    ): Sequence<KoObjectDeclarationImpl> =
        koFiles.flatMap { it.objects(modifiers, includeNested) }

    override fun companionObjects(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
    ): Sequence<KoCompanionObjectDeclarationImpl> =
        koFiles.flatMap { it.companionObjects(modifiers, includeNested) }

    override fun functions(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoFunctionDeclarationImpl> =
        koFiles.flatMap { it.functions(modifiers, includeNested, includeLocal) }

    override fun declarations(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
    ): Sequence<KoDeclarationImpl> =
        koFiles.flatMap { it.declarations(modifiers, includeNested) }

    override fun properties(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoPropertyDeclarationImpl> =
        koFiles.flatMap { it.properties(modifiers, includeNested, includeLocal) }

    override fun imports() = koFiles.flatMap { it.imports }

    override fun packages() = koFiles.mapNotNull { it.packagee }

    override fun typeAliases() = koFiles.flatMap { it.typeAliases }

    override operator fun plus(scope: KoScope): KoScope = KoScopeImpl(files() + scope.files())

    override operator fun minus(scope: KoScope): KoScope = KoScopeImpl(files() - scope.files().toSet())

    override operator fun plusAssign(scope: KoScope) {
        koFiles += scope.files()
    }

    override operator fun minusAssign(scope: KoScope) {
        koFiles -= scope.files()
    }

    override fun toString(): String = files()
        .toList()
        .joinToString("\n") { it.filePath }

    override fun print() {
        println(toString())
    }
}
