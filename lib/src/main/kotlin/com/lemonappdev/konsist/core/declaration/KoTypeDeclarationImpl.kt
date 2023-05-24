package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationImpl private constructor(
    private val ktTypeReference: KtTypeReference,
) :
    KoNamedDeclarationImpl(ktTypeReference),
    KoTypeDeclaration {
    private val file = KoFileImpl(ktTypeReference.containingKtFile)

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

    override val fullyQualifiedName by lazy {
        file
            .imports
            .map { it.name }
            .firstOrNull() { it.contains(sourceType) } ?: ""
    }

    override fun isImportAlias() = importAliasName.isNotEmpty()

    internal companion object {
        private val cache = KoDeclarationCache<KoTypeDeclarationImpl>()

        internal fun getInstance(ktTypeReference: KtTypeReference, parent: KoParent) =
            cache.getOrCreateInstance(ktTypeReference, parent) { KoTypeDeclarationImpl(ktTypeReference) }
    }
}
