package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAliasProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoWildcardProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtImportDirective

internal class KoImportDeclarationImpl private constructor(override val ktImportDirective: KtImportDirective) :
    KoImportDeclaration,
    KoBaseProviderCore,
    KoAliasProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoWildcardProviderCore {
    override val psiElement: PsiElement by lazy { ktImportDirective }

    override val ktElement: KtElement by lazy { ktImportDirective }

    override val name: String by lazy { ktImportDirective.importPath?.fqName.toString() }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoImportDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktImportDirective: KtImportDirective,
            parent: KoParentProvider,
        ): KoImportDeclaration =
            cache.getOrCreateInstance(ktImportDirective, parent) { KoImportDeclarationImpl(ktImportDirective) }
    }
}
