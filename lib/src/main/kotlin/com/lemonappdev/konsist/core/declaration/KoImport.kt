package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtImportDirective

class KoImport private constructor(private val ktImportDirective: KtImportDirective) : KoNamedDeclaration(ktImportDirective) {
    override val name by lazy { ktImportDirective.importPath?.fqName.toString() }

    val alias by lazy { ktImportDirective.alias?.name ?: name }

    companion object {
        private val cache = KoDeclarationCache<KoImport>()

        fun getInstance(ktImportDirective: KtImportDirective) =
            cache.getOrCreateInstance(ktImportDirective) { KoImport(ktImportDirective) }
    }
}
