package com.lemonappdev.konsist.core.declaration.type

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoExternalTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoExternalTypeCache
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoNonNullableTypeProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtImportAlias
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoImportAliasDeclarationCore private constructor(
    private val ktImportAlias: KtImportAlias,
) :
    KoImportAliasDeclaration,
    KoTypeDeclarationCore,
    KoBaseProviderCore,
    KoNonNullableTypeProviderCore {
    override val psiElement: PsiElement by lazy { ktImportAlias }

    override val ktElement: KtElement by lazy { ktImportAlias }

    override val name: String by lazy { ktImportAlias.text }
    override val type: KoTypeDeclaration by lazy {
        val types = ktImportAlias
            .children
            .filterIsInstance<KtTypeReference>()

        TypeUtil.getType(
            types,
            ktImportAlias.isExtensionDeclaration(),
            this.castToKoBaseDeclaration(),
            containingFile,
        ) ?: throw IllegalArgumentException("Import alias has no specified type.")
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoImportAliasDeclaration> = KoDeclarationCache() // Todo: change this?

        internal fun getInstance(
            ktImportAlias: KtImportAlias,
            containingDeclaration: KoBaseDeclaration,
        ): KoImportAliasDeclaration =
            cache.getOrCreateInstance(ktImportAlias, containingDeclaration) {
                KoImportAliasDeclarationCore(
                    ktImportAlias,
                )
            }
    }
}
