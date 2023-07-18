package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.container.KoFileImpl
import org.jetbrains.kotlin.psi.KtTypeReference

internal class KoTypeDeclarationImpl private constructor(
    private val ktTypeReference: KtTypeReference,
) :
    KoNamedDeclarationImpl(ktTypeReference),
    KoTypeDeclaration {
    private val file: KoFile by lazy { KoFileImpl(ktTypeReference.containingKtFile) }

    override val aliasType: String? by lazy {
        file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text.removeSuffix("?") }
            ?.alias
    }

    override val name: String by lazy {
        when {
            isAlias() -> aliasType + if (isNullable) "?" else ""
            else -> ktTypeReference.text
        }
    }

    override val sourceType: String by lazy {
        if (isAlias()) {
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

    override val isKotlinType: Boolean by lazy {
        if (isAlias()) {
            false
        } else {
            val parts = sourceType.split("<", ">", " ", ",")
            parts.any { basicTypes.any { basicType -> basicType == it } } ||
                parts.any { collections.any { collection -> collection == it } }
        }
    }

    // Basic types in Kotlin are described here: https://kotlinlang.org/docs/basic-types.html
    private val basicTypes: List<String> by lazy {
        listOf(
            "Byte",
            "Short",
            "Int",
            "Long",
            "Float",
            "Double",
            "UByte",
            "UShort",
            "UInt",
            "ULong",
            "UByteArray",
            "UShortArray",
            "UIntArray",
            "ULongArray",
            "Boolean",
            "Char",
            "String",
            "Array",
        )
    }

    // Collections in Kotlin are described here: https://kotlinlang.org/docs/collections-overview.html#collection
    private val collections: List<String> by lazy {
        listOf(
            "Iterable",
            "MutableIterable",
            "Collection",
            "MutableCollection",
            "List",
            "MutableList",
            "Map",
            "MutableMap",
            "Set",
            "MutableSet",
        )
    }

    override val isGenericType: Boolean by lazy {
        val regex = "\\w+<[^<>]+>".toRegex()

        regex.matches(sourceType)
    }

    override val fullyQualifiedName: String by lazy {
        file
            .imports
            .map { it.name }
            .firstOrNull { it.contains(sourceType) } ?: ""
    }

    override fun isAlias(): Boolean = aliasType != null

    internal companion object {
        private val cache: KoDeclarationCache<KoTypeDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktTypeReference: KtTypeReference, parentDeclaration: KoBaseDeclaration?): KoTypeDeclaration =
            cache.getOrCreateInstance(ktTypeReference, parentDeclaration) { KoTypeDeclarationImpl(ktTypeReference) }
    }
}
