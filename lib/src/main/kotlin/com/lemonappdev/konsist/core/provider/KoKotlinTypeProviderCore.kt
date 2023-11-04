package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider

internal interface KoKotlinTypeProviderCore : KoKotlinTypeProvider, KoSourceAndAliasTypeProviderCore, KoBaseProviderCore {
    override val isKotlinType: Boolean
        get() = if (isAlias) {
            false
        } else {
            val types = basicTypes + collections
            types.any { it == sourceType.substringBefore('<') }
        }

    // Basic types in Kotlin are described here: https://kotlinlang.org/docs/basic-types.html
    private val basicTypes: List<String>
        get() = listOf(
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

    // Collections in Kotlin are described here: https://kotlinlang.org/docs/collections-overview.html#collection
    private val collections: List<String>
        get() = listOf(
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
