package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
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
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtPsiUtil

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
    KoIsWildcardProviderCore,
    KoSourceDeclarationProviderCore {
    override val psiElement: PsiElement
        get() = ktImportDirective

    override val ktElement: KtElement
        get() = ktImportDirective

    /*
    Name is built from identifiers of the import reference AST nodes. 'KtImportDirective.importPath' is not used,
    because it creates PSI element for every name segment and caches FqName (with all its parents) in the import
    directive, which significantly increases memory usage of the project with many imports.
     */
    override val name: String by lazy {
        val identifiers =
            ktImportDirective
                .importedReference
                ?.node
                ?.let { getIdentifiers(it) }
                .orEmpty()

        // Import without reference has no import path, so its name is "null"
        identifiers.joinToString(".").ifEmpty { null.toString() }
    }

    override val alias: KoImportAliasDeclaration? by lazy {
        ktImportDirective
            .alias
            ?.let { KoImportAliasDeclarationCore.getInstance(it, this) }
    }

    override val sourceDeclaration: KoDeclarationCastProvider? by lazy {
        val shortName = name.substringAfterLast(".")

        (
            DataCore
                .declarations
                .filterIsInstance<KoFullyQualifiedNameProvider>()
                .firstOrNull { it.fullyQualifiedName == name }
                as? KoDeclarationCastProvider
                ?: getKotlinType(shortName)
                ?: KoExternalDeclarationCore.getInstance(shortName, ktImportDirective)
        )
            as? KoDeclarationCastProvider
    }

    private fun getIdentifiers(node: ASTNode): List<String> =
        if (node.elementType == KtTokens.IDENTIFIER) {
            listOf(KtPsiUtil.unquoteIdentifierOrFieldReference(node.text))
        } else {
            node.getChildren(null).flatMap { getIdentifiers(it) }
        }

    private fun getKotlinType(name: String): KoKotlinTypeDeclaration? =
        if (TypeUtil.isKotlinType(name)) {
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
    override fun representsType(
        name: String?,
        ignoreCase: Boolean,
    ): Boolean = name?.let { this.name.endsWith(it, ignoreCase) } ?: false

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
