package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationCore
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

internal interface KoImportProviderCore : KoImportProvider, KoContainingDeclarationProviderCore, KoBaseProviderCore {
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

            return ktImportDirectives.map { KoImportDeclarationCore.getInstance(it, this) }
        }

    override val numImports: Int
        get() = imports.size

    override fun countImports(predicate: (KoImportDeclaration) -> Boolean): Int =
        imports.count { predicate(it) }

    override fun hasImports(vararg names: String): Boolean = when {
        names.isEmpty() -> imports.isNotEmpty()
        else -> names.all {
            imports.any { import -> LocationUtil.resideInLocation(it, import.name) }
        }
    }
}
