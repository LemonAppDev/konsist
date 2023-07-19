package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAliasProviderCore
import com.lemonappdev.konsist.core.provider.KoWildcardProviderCore
import org.jetbrains.kotlin.psi.KtImportDirective

internal class KoImportDeclarationImpl private constructor(override val ktImportDirective: KtImportDirective) :
    KoBaseDeclarationImpl(ktImportDirective),
    KoAliasProviderCore,
    KoWildcardProviderCore,
    KoImportDeclaration {
    override val name: String by lazy { ktImportDirective.importPath?.fqName.toString() }

    internal companion object {
        private val cache: KoDeclarationCache<KoImportDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktImportDirective: KtImportDirective,
            parentDeclaration: KoParentDeclarationProvider?,
        ): KoImportDeclaration =
            cache.getOrCreateInstance(ktImportDirective, parentDeclaration) { KoImportDeclarationImpl(ktImportDirective) }
    }
}
