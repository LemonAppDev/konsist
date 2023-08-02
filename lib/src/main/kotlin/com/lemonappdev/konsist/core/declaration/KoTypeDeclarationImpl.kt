package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoKotlinTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationImpl private constructor(
    override val ktTypeReference: KtTypeReference,
) :
    KoTypeDeclaration,
    KoBaseProviderCore,
    KoContainingFileProviderCore,
    KoFullyQualifiedNameProviderCore,
    KoGenericTypeProviderCore,
    KoKotlinTypeProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoNullableProviderCore,
    KoPathProviderCore,
    KoSourceAndAliasTypeProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktTypeReference }

    override val ktElement: KtElement by lazy { ktTypeReference }

    private val file: KoFile by lazy { KoFileImpl(ktTypeReference.containingKtFile) }

    override val name: String by lazy {
        when {
            isAlias -> aliasType + if (isNullable) "?" else ""
            else -> ktTypeReference.text
        }
    }

    override val fullyQualifiedName: String by lazy {
        file
            .imports
            .map { it.name }
            .firstOrNull { it.contains(sourceType) } ?: ""
    }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeReference: KtTypeReference, parent: KoContainingDeclarationProvider?): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, parent) { KoTypeDeclarationImpl(ktTypeReference) }
    }
}
