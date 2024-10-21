package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoBaseTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtImportAlias

internal class KoImportAliasDeclarationCore private constructor(
    private val ktImportAlias: KtImportAlias,
    override val containingDeclaration: KoImportDeclaration,
) : KoImportAliasDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoSourceDeclarationProviderCore {
    override val psiElement: PsiElement by lazy { ktImportAlias }

    override val ktElement: KtElement by lazy { ktImportAlias }

    override val text: String by lazy { ktImportAlias.name ?: ktImportAlias.text }

    override val name: String by lazy { text }

    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val importDirective: KoImportDeclaration by lazy { containingDeclaration }

    override val sourceDeclaration: KoSourceDeclaration? by lazy { importDirective.sourceDeclaration }

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoImportAliasDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktImportAlias: KtImportAlias,
            containingDeclaration: KoImportDeclaration,
        ): KoImportAliasDeclaration =
            cache.getOrCreateInstance(ktImportAlias, containingDeclaration) {
                KoImportAliasDeclarationCore(ktImportAlias, containingDeclaration)
            }
    }
}
