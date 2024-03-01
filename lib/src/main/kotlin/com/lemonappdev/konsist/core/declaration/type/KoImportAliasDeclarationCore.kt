package com.lemonappdev.konsist.core.declaration.type

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNonNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoImportAliasDeclarationCore private constructor(
    private val ktUserType: KtUserType,
) :
    KoImportAliasDeclaration,
    KoBaseTypeDeclarationCore,
    KoBaseProviderCore,
    KoNonNullableTypeProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore {
    override val psiElement: PsiElement by lazy { ktUserType }

    override val ktElement: KtElement by lazy { ktUserType }

//    override val name: String by lazy { ktUserType.text }
//
//    override val packagee: KoPackageDeclaration? by lazy { containingFile.packagee }

    override val type: KoTypeDeclaration by lazy {
        val type = ktUserType
            .children
            .filterIsInstance<KtTypeReference>()
            .firstOrNull()

        type?.let { KoTypeDeclarationCore.getInstance(it, containingDeclaration as KoContainingDeclarationProvider) }
            ?: throw IllegalArgumentException("Import alias has no specified type.")
    }

//    override val importDirective: KoImportDeclaration by lazy {
//        containingFile.imports.first {
//            it.alias == name
//        }
//    }

    override fun toString(): String = text // Todo: change to name

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
