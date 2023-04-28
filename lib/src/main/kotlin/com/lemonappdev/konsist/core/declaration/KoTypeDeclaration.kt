package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtTypeReference

class KoTypeDeclaration private constructor(private val ktTypeReference: KtTypeReference) : KoNamedDeclaration(ktTypeReference) {
    private val file = KoFileDeclaration.getInstance(ktTypeReference.containingKtFile)

    val importAliasName: String by lazy {
        file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text }
            ?.alias ?: ""
    }

    override val name: String by lazy {
        when {
            isImportAlias() -> importAliasName
            else -> sourceType
        }
    }

    val sourceType: String by lazy {
        if (importAliasName.isNotEmpty()) {
            file
                .imports
                .first { it.alias == ktTypeReference.text }
                .name
                .split(".")
                .toMutableList()
                .last { it.isNotBlank() }
        } else {
            ktTypeReference.text
        }
    }

    val fullyQualifiedName by lazy {
        file
            .imports
            .map { it.name }
            .first { it.contains(sourceType) }
    }

    fun isImportAlias() = importAliasName.isNotEmpty()

    companion object {
        private val cache = KoDeclarationCache<KoTypeDeclaration>()

        fun getInstance(ktTypeReference: KtTypeReference) =
            cache.getOrCreateInstance(ktTypeReference) { KoTypeDeclaration(ktTypeReference) }
    }
}
