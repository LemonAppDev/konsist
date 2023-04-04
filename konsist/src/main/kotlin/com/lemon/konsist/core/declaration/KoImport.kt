package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtImportDirective

class KoImport private constructor(private val ktImportDirective: KtImportDirective) : KoNamedDeclaration(ktImportDirective) {
    override val name by lazy { ktImportDirective.importPath?.fqName.toString() }

    companion object {
        private val cache = KoDeclarationCache<KoImport>()
        fun getInstance(ktImportDirective: KtImportDirective) = if (cache.hasKey(ktImportDirective)) {
            cache.get(ktImportDirective)
        } else {
            cache.set(ktImportDirective, KoImport(ktImportDirective))
            cache.get(ktImportDirective)
        }
    }
}
