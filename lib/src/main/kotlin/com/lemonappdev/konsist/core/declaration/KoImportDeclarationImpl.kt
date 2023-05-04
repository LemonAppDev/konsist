package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtImportDirective

internal class KoImportDeclarationImpl private constructor(private val ktImportDirective: KtImportDirective) :
    KoNamedDeclarationImpl(ktImportDirective),
    KoImportDeclaration {
    override val name by lazy { ktImportDirective.importPath?.fqName.toString() }

    override val alias by lazy { ktImportDirective.alias?.name ?: name }

    internal companion object {
        private val cache = KoDeclarationCache<KoImportDeclarationImpl>()

        internal fun getInstance(ktImportDirective: KtImportDirective, parent: KoBaseDeclarationImpl) =
            cache.getOrCreateInstance(ktImportDirective, parent) { KoImportDeclarationImpl(ktImportDirective) }
    }
}
