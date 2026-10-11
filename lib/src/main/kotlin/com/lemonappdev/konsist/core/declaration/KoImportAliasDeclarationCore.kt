package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
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
import org.jetbrains.kotlin.psi.KtTypeProjection

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
    override val psiElement: PsiElement
        get() = ktImportAlias

    override val ktElement: KtElement
        get() = ktImportAlias

    override val ktTypeProjection: KtTypeProjection?
        get() = null

    @Volatile
    private var cachedText: Any? = KoPropertyCache.NOT_COMPUTED

    override val text: String
        get() = KoPropertyCache.getOrCompute(cachedText, { cachedText = it }) { ktImportAlias.name ?: ktImportAlias.text }

    override val name: String
        get() = text

    @Volatile
    private var cachedPackagee: Any? = KoPropertyCache.NOT_COMPUTED

    override val packagee: KoPackageDeclaration?
        get() = KoPropertyCache.getOrCompute(cachedPackagee, { cachedPackagee = it }) { containingFile.packagee }

    override val importDirective: KoImportDeclaration
        get() = containingDeclaration

    @Volatile
    private var cachedSourceDeclaration: Any? = KoPropertyCache.NOT_COMPUTED

    override val sourceDeclaration: KoDeclarationCastProvider?
        get() =
            KoPropertyCache.getOrCompute(cachedSourceDeclaration, { cachedSourceDeclaration = it }) {
                importDirective.sourceDeclaration
            }

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
