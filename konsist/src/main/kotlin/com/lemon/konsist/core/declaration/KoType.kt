package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtTypeReference

class KoType private constructor(private val ktTypeReference: KtTypeReference) : KoNamedDeclaration(ktTypeReference) {
    private val file = KoFile.getInstance(ktTypeReference.containingKtFile)

    val aliasName: String? by lazy {
        file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text }
            ?.alias
    }

    override val name: String by lazy {
        if (aliasName != null) {
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
        name.let {
            file
                .imports
                .map { it.name }
                .first { it.contains(name) }
        }
    }

    val isTypeAlias by lazy { aliasName != null }

    companion object {
        private val cache = KoDeclarationCache<KoType>()
        fun getInstance(ktTypeReference: KtTypeReference) =
            cache.getOrCreateInstance(ktTypeReference) { KoType(ktTypeReference) }
    }
}
