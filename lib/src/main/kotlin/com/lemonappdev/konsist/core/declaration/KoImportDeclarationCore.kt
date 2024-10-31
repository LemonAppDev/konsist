package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.annotation.RemoveInVersion
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.model.DataCore
import com.lemonappdev.konsist.core.provider.KoAliasProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoIsWildcardProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoMatchesProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoWildcardProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtNameReferenceExpression

internal class KoImportDeclarationCore private constructor(
    override val ktImportDirective: KtImportDirective,
) : KoImportDeclaration,
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
    KoWildcardProviderCore,
    KoIsWildcardProviderCore,
    KoSourceDeclarationProviderCore {
    override val psiElement: PsiElement by lazy { ktImportDirective }

    override val ktElement: KtElement by lazy { ktImportDirective }

    override val name: String by lazy { ktImportDirective.importPath?.fqName.toString() }

    override val alias: KoImportAliasDeclaration? by lazy {
        ktImportDirective
            .alias
            ?.let { KoImportAliasDeclarationCore.getInstance(it, this) }
    }

    @RemoveInVersion("0.18.0")
    override val isWildcard: Boolean by lazy { super<KoIsWildcardProviderCore>.isWildcard }

    override val sourceDeclaration: KoSourceDeclaration by lazy {
        val shortName = name.substringAfterLast(".")

        DataCore
            .declarations
            .filterIsInstance<KoFullyQualifiedNameProvider>()
            .firstOrNull { it.fullyQualifiedName == name }
                as? KoSourceDeclaration
            ?: getKotlinType(shortName)
            ?: KoExternalDeclarationCore.getInstance(shortName, ktImportDirective)
    }

    private fun getKotlinType(name: String): KoKotlinTypeDeclaration? =
        if (TypeUtil.isKotlinBasicType(name) || TypeUtil.isKotlinCollectionTypes(name)) {
            val ktNameReferenceExpression =
                ktImportDirective
                    .children
                    .filterIsInstance<KtDotQualifiedExpression>()
                    .flatMap { it.children.toList() }
                    .filterIsInstance<KtNameReferenceExpression>()
                    .lastOrNull()

            ktNameReferenceExpression?.let {
                KoKotlinTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
            }
        } else {
            null
        }

    // KoImportDeclarationCore does not implement KoRepresentsTypeProviderCore because it internally implements
    // KoFullyQualifiedNameProviderCore, which import declaration does not possess. Therefore, this function is manually overridden.
    override fun representsType(name: String?): Boolean = name?.let { this.name.endsWith(it) } ?: false

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoImportDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktImportDirective: KtImportDirective,
            containingDeclaration: KoBaseDeclaration,
        ): KoImportDeclaration =
            cache.getOrCreateInstance(ktImportDirective, containingDeclaration) {
                KoImportDeclarationCore(ktImportDirective)
            }
    }
}
