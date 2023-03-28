package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtImportDirective

class KoImport(
    private val ktImportDirective: KtImportDirective,
) : KoNamedDeclaration(ktImportDirective) {
    override val name by lazy { ktImportDirective.importPath?.fqName.toString() }
}
