package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationImpl private constructor(
    private val ktTypeReference: KtTypeReference,
) :
    KoNamedDeclarationImpl(ktTypeReference),
    KoTypeDeclaration {
    private val file: KoFileDeclaration by lazy {
        KoFileDeclarationImpl.getInstance(ktTypeReference.containingKtFile, this)
    }

    override val importAliasName: String by lazy {
        file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text.removeSuffix("?") }
            ?.alias ?: ""
    }

    override val name: String by lazy {
        when {
            isImportAlias() -> importAliasName + if (isNullable) "?" else ""
            else -> ktTypeReference.text
        }
    }

    override val sourceType: String by lazy {
        if (isImportAlias()) {
            file
                .imports
                .first { it.alias == ktTypeReference.text.removeSuffix("?") }
                .name
                .split(".")
                .toMutableList()
                .last { it.isNotBlank() }
        } else {
            name
                .removeSuffix("?")
        }
    }

    override val isNullable: Boolean by lazy { ktTypeReference.text.last() == '?' }

    override val fullyQualifiedName: String by lazy {
        file
            .imports
            .map { it.name }
            .firstOrNull() { it.contains(sourceType) } ?: ""
    }

    override fun isImportAlias(): Boolean = importAliasName.isNotEmpty()

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeReference: KtTypeReference, parent: KoBaseDeclaration): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, parent) { KoTypeDeclarationImpl(ktTypeReference) }
    }
}
