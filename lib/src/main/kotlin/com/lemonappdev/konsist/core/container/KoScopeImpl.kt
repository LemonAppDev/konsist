package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoImportProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoObjectProviderCore
import com.lemonappdev.konsist.core.provider.KoPackagesProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeAliasProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtFile

@Suppress("detekt.TooManyFunctions")
class KoScopeImpl(
    override var koFiles: List<KoFileDeclaration>,
) : KoScope,
    KoAnnotationProviderCore,
    KoClassProviderCore,
    KoDeclarationProviderCore,
    KoFileProviderCore,
    KoFunctionProviderCore,
    KoImportProviderCore,
    KoInterfaceProviderCore,
    KoObjectProviderCore,
    KoPackagesProviderCore,
    KoPropertyProviderCore,
    KoTypeAliasProviderCore {
    constructor(koFileDeclaration: KoFileDeclaration) : this(listOf(koFileDeclaration))

    override val ktFile: KtFile? by lazy { null }

    override val parent: KoParentProvider? by lazy { null }

    override val ktAnnotated: KtAnnotated? by lazy { null }

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
        koFiles.flatMap { it.declarations(includeNested, includeLocal) }

    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoPropertyDeclaration> =
        koFiles.flatMap { it.properties(includeNested, includeLocal) }

    override fun slice(predicate: (KoFileDeclaration) -> Boolean): KoScope = KoScopeImpl(koFiles.filter { predicate(it) })

    override operator fun plus(scope: KoScope): KoScope = KoScopeImpl(files + scope.files)

    override operator fun minus(scope: KoScope): KoScope = KoScopeImpl(files - scope.files.toSet())

    override operator fun plusAssign(scope: KoScope) {
        koFiles += scope.files
    }

    override operator fun minusAssign(scope: KoScope) {
        koFiles -= scope.files
    }

    override fun toString(): String = files
        .joinToString("\n") { it.path }

    override fun print() {
        println(toString())
    }

    override fun equals(other: Any?): Boolean = other is KoScope && files == other.files

    override fun hashCode(): Int = 31 * 7 + files.hashCode()
}
