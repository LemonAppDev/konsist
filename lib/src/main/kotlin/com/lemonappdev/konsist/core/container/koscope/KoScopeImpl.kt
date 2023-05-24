package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

@Suppress("detekt.TooManyFunctions")
class KoScopeImpl(
    private var koFiles: Sequence<KoFile>,
) : KoScope {
    constructor(koFile: KoFile) : this(sequenceOf(koFile))

    override fun files(): Sequence<KoFile> = koFiles.sortedBy { it.path }

    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoClassDeclaration> =
        koFiles.flatMap { it.classes(includeNested, includeLocal) }

    override fun interfaces(
        includeNested: Boolean,
    ): Sequence<KoInterfaceDeclaration> =
        koFiles.flatMap { it.interfaces(includeNested) }

    override fun objects(
        includeNested: Boolean,
    ): Sequence<KoObjectDeclaration> =
        koFiles.flatMap { it.objects(includeNested) }

    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoFunctionDeclaration> =
        koFiles.flatMap { it.functions(includeNested, includeLocal) }

    override fun namedDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoNamedDeclaration> =
        koFiles.flatMap { it.declarations(includeNested, includeLocal) }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoDeclaration> = namedDeclarations(includeNested, includeLocal)
        .filterIsInstance<KoDeclaration>()

    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoPropertyDeclaration> =
        koFiles.flatMap { it.properties(includeNested, includeLocal) }

    override fun slice(predicate: (KoFile) -> Boolean): KoScope = KoScopeImpl(koFiles.filter { predicate(it) })

    override fun imports() = koFiles.flatMap { it.imports }

    override fun annotations() = koFiles.flatMap { it.annotations }

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
        .joinToString("\n") { it.path }

    override fun print() {
        println(toString())
    }

    override fun equals(other: Any?): Boolean = other is KoScope && files().toList() == other.files().toList()

    override fun hashCode(): Int = 31 * 7 + files().toList().hashCode()
}
