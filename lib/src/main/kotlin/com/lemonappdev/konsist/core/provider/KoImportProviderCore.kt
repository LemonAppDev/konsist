package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

internal interface KoImportProviderCore :
    KoImportProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktFile: KtFile

    override val imports: List<KoImportDeclaration>
        get() {
            val ktImportDirectives =
                ktFile
                    .children
                    .filterIsInstance<KtImportList>()
                    .first()
                    .children
                    .filterIsInstance<KtImportDirective>()

            return ktImportDirectives.map { KoImportDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
        }

    override val numImports: Int
        get() = imports.size

    override fun countImports(predicate: (KoImportDeclaration) -> Boolean): Int = imports.count { predicate(it) }

    override fun hasImports(): Boolean = imports.isNotEmpty()

    override fun hasImportWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasImportWithName(listOf(name, *names), ignoreCase)

    override fun hasImportWithName(names: Collection<String>, ignoreCase: Boolean,): Boolean =
        when {
            names.isEmpty() -> hasImports()
            else ->
                names.any {
                    imports.any { import -> import.hasName(it, ignoreCase) }
                }
        }

    override fun hasImportsWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasImportsWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasImportsWithAllNames(names: Collection<String>, ignoreCase: Boolean,): Boolean =
        when {
            names.isEmpty() -> hasImports()
            else ->
                names.all {
                    imports.any { import -> import.hasName(it, ignoreCase) }
                }
        }

    override fun hasImport(predicate: (KoImportDeclaration) -> Boolean): Boolean = imports.any(predicate)

    override fun hasAllImports(predicate: (KoImportDeclaration) -> Boolean): Boolean = imports.all(predicate)
}
