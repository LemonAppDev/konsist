package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtTypeReference

class KoType private constructor(private val ktTypeReference: KtTypeReference) : KoNamedDeclaration(ktTypeReference) {
    private val file = KoFile.getInstance(ktTypeReference.containingKtFile)

    override val name: String by lazy {
        file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text }
            ?.alias ?: ""
    }

    val sourceType: String by lazy {
        if (name.isNotEmpty()) {
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

    fun isImportAlias() = name.isNotEmpty()

    companion object {
        private val cache = KoDeclarationCache<KoType>()

        fun getInstance(ktTypeReference: KtTypeReference) =
            cache.getOrCreateInstance(ktTypeReference) { KoType(ktTypeReference) }
    }
}
