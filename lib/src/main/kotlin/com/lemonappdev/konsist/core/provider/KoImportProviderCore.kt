package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider
import com.lemonappdev.konsist.core.declaration.KoImportDeclarationImpl
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportList

internal interface KoImportProviderCore : KoImportProvider, KoParentProviderCore, KoBaseProviderCore {
    val ktFile: KtFile?
    val koFiles: List<KoFile>?

    override val imports: List<KoImportDeclaration>
        get() {
            val ktImportDirectives =
                ktFile
                    ?.children
                    ?.filterIsInstance<KtImportList>()
                    ?.first()
                    ?.children
                    ?.filterIsInstance<KtImportDirective>()

            val imports = if (ktFile != null) {
                ktImportDirectives?.map { KoImportDeclarationImpl.getInstance(it, null) }
            } else {
                koFiles?.flatMap { (it as KoImportProvider).imports }
            }

            return imports ?: emptyList()
        }

    override fun hasImports(vararg names: String): Boolean = when {
        names.isEmpty() -> imports.toList().isNotEmpty()
        else -> names.all {
            imports.any { import -> LocationUtil.resideInLocation(it, import.name) }
        }
    }
}
