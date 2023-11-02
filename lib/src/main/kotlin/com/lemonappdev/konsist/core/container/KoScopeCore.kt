package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

@Suppress("detekt.TooManyFunctions")
class KoScopeCore(
    private var koFiles: List<KoFileDeclaration>,
) : KoScope {
    constructor(koFileDeclaration: KoFileDeclaration) : this(listOf(koFileDeclaration))

    override val files: List<KoFileDeclaration> by lazy { koFiles.sortedBy { it.path } }

    override val imports: List<KoImportDeclaration> by lazy { koFiles.flatMap { it.imports } }

    override val annotations: List<KoAnnotationDeclaration> by lazy { koFiles.flatMap { it.annotations } }

    override val packages: List<KoPackageDeclaration> by lazy { koFiles.mapNotNull { it.packagee } }

    override val typeAliases: List<KoTypeAliasDeclaration> by lazy { koFiles.flatMap { it.typeAliases } }

    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassDeclaration> =
        koFiles.flatMap { it.classes(includeNested, includeLocal) }

    override fun interfaces(
        includeNested: Boolean,
    ): List<KoInterfaceDeclaration> =
        koFiles.flatMap { it.interfaces(includeNested) }

    override fun objects(
        includeNested: Boolean,
    ): List<KoObjectDeclaration> =
        koFiles.flatMap { it.objects(includeNested) }

    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoFunctionDeclaration> =
        koFiles.flatMap { it.functions(includeNested, includeLocal) }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> =
        koFiles.flatMap { listOf(it) + it.declarations(includeNested, includeLocal) }

    override fun properties(
        includeNested: Boolean,
    ): List<KoPropertyDeclaration> =
        koFiles.flatMap { it.properties(includeNested) }

    override fun slice(predicate: (KoFileDeclaration) -> Boolean): KoScope =
        KoScopeCore(koFiles.filter { predicate(it) })

    override operator fun plus(scope: KoScope): KoScope = KoScopeCore(files + scope.files)

    override operator fun minus(scope: KoScope): KoScope = KoScopeCore(files - scope.files.toSet())

    override operator fun plusAssign(scope: KoScope) {
        koFiles += scope.files
    }

    override operator fun minusAssign(scope: KoScope) {
        koFiles -= scope.files
    }

    override fun toString(): String = files
        .toList()
        .joinToString("\n") { it.path }

    override fun print(prefix: String?, predicate: ((KoScope) -> String)?): KoScope {
        prefix?.let { println(it) }

        if (predicate != null) {
            println(predicate(this))
        } else {
            println(toString())
        }

        return this
    }

    override fun equals(other: Any?): Boolean = other is KoScope && files.toList() == other.files.toList()

    override fun hashCode(): Int = 31 * 7 + files.toList().hashCode()
}
