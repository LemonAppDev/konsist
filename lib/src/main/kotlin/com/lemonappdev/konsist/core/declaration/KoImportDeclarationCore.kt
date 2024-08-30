package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAliasProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoMatchesProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoWildcardProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtImportDirective

internal class KoImportDeclarationCore private constructor(override val ktImportDirective: KtImportDirective) :
    KoImportDeclaration,
    KoBaseProviderCore,
    KoAliasProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoMatchesProviderCore,
    KoNameProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore,
    KoWildcardProviderCore {
        override val psiElement: PsiElement by lazy { ktImportDirective }

        override val ktElement: KtElement by lazy { ktImportDirective }

        override val name: String by lazy { ktImportDirective.importPath?.fqName.toString() }

        override val alias: KoImportAliasDeclaration? by lazy {
            ktImportDirective
                .alias
                ?.let { KoImportAliasDeclarationCore.getInstance(it, this) }
        }

        override fun toString(): String = name

        internal companion object {
            private val cache: KoDeclarationCache<KoImportDeclaration> = KoDeclarationCache()

            internal fun getInstance(
                ktImportDirective: KtImportDirective,
                containingDeclaration: KoBaseDeclaration,
            ): KoImportDeclaration =
                cache.getOrCreateInstance(ktImportDirective, containingDeclaration) { KoImportDeclarationCore(ktImportDirective) }
        }
    }
