package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoWildcardProvider
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
    KoAliasProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoPathProviderCore,
    KoTextProviderCore,
    KoBaseProviderCore,
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
            parentDeclaration: KoParentDeclarationProvider?,
        ): KoImportDeclaration =
            cache.getOrCreateInstance(ktImportDirective, parentDeclaration) { KoImportDeclarationImpl(ktImportDirective) }
    }
}
