package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtImportDirective

internal class KoImportDeclarationImpl private constructor(private val ktImportDirective: KtImportDirective) :
    KoNamedDeclarationImpl(ktImportDirective),
    KoImportDeclaration {
    override val name: String by lazy { ktImportDirective.importPath?.fqName.toString() }

    override val alias: String by lazy { ktImportDirective.alias?.name ?: name }

    override val isWildcard: Boolean by lazy { ktImportDirective.text.endsWith('*') }

    internal companion object {
        private val cache = KoDeclarationCache<KoImportDeclarationImpl>()

        internal fun getInstance(ktImportDirective: KtImportDirective, parent: KoBaseDeclaration) =
            cache.getOrCreateInstance(ktImportDirective, parent) { KoImportDeclarationImpl(ktImportDirective) }
    }
}
