package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.provider.KoFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoGenericTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoKotlinTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceAndAliasTypeProviderCore
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationImpl private constructor(
    override val ktTypeReference: KtTypeReference,
) :
    KoBaseDeclarationImpl(ktTypeReference),
    KoTypeDeclaration,
    KoFullyQualifiedNameProviderCore,
    KoParentProviderCore,
    KoNullableProviderCore,
    KoSourceAndAliasTypeProviderCore,
    KoKotlinTypeProviderCore,
    KoGenericTypeProviderCore {
    private val file: KoFile by lazy { KoFileImpl(ktTypeReference.containingKtFile) }

    override val name: String by lazy {
        when {
            isAlias() -> aliasType + if (isNullable) "?" else ""
            else -> ktTypeReference.text
        }
    }

    override val fullyQualifiedName: String by lazy {
        file
            .imports
            .map { it.name }
            .firstOrNull { it.contains(sourceType) } ?: ""
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeReference: KtTypeReference, parentDeclaration: KoParentProvider?): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, parentDeclaration) { KoTypeDeclarationImpl(ktTypeReference) }
    }
}
