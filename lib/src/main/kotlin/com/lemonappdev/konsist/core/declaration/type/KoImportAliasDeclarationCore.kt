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
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoImportAliasDeclarationCore private constructor(
    private val ktUserType: KtUserType,
) :
    KoImportAliasDeclaration,
    KoTypeDeclarationCore,
    KoBaseProviderCore,
    KoNonNullableTypeProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

    override val name: String by lazy { ktUserType.text }
    override val type: KoTypeDeclaration by lazy {
        val types = ktUserType
            .children
            .filterIsInstance<KtTypeReference>()

        TypeUtil.getType(
            types,
            ktUserType.isExtensionDeclaration(),
            this.castToKoBaseDeclaration(),
            containingFile,
        ) ?: throw IllegalArgumentException("Import alias has no specified type.")
    }

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoImportAliasDeclaration> = KoDeclarationCache() // Todo: change this?

        internal fun getInstance(
            ktUserType: KtUserType,
            containingDeclaration: KoBaseDeclaration,
        ): KoImportAliasDeclaration =
            cache.getOrCreateInstance(ktUserType, containingDeclaration) {
                KoImportAliasDeclarationCore(
                    ktUserType,
                )
            }
    }
}
