package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider

internal interface KoKotlinTypeProviderCore :
    KoKotlinTypeProvider,
    KoSourceAndAliasTypeProviderCore,
    KoBaseProviderCore {
    // Basic types in Kotlin are described here: https://kotlinlang.org/docs/basic-types.html
    private val kotlinBasicTypes: Set<String>
        get() = setOf(
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
        )

    // Collections in Kotlin are described here: https://kotlinlang.org/docs/collections-overview.html#collection
    // and here https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections
    private val kotlinCollectionTypes: Set<String>
        get() = setOf(
            "AbstractCollection",
            "AbstractIterator",
            "AbstractList",
            "AbstractMap",
            "AbstractMutableCollection",
            "AbstractMutableList",
            "AbstractMutableMap",
            "AbstractMutableSet",
            "AbstractSet",
            "ArrayDeque",
            "ArrayList",
            "Array",
            "Collection",
            "HashMap",
            "HashSet",
            "LinkedHashMap",
            "LinkedHashSet",
            "List",
            "Map",
            "MutableCollection",
            "MutableList",
            "MutableMap",
            "MutableSet",
            "Set",
        )

    override val isKotlinType: Boolean
        get() = if (isAlias) {
            false
        } else {
            isKotlinBasicType || isKotlinCollectionType
        }

    override val isKotlinBasicType: Boolean
        get() = if (isAlias) {
            false
        } else {
            val rawSourceType = baseSourceType.replace("?", "")
            kotlinBasicTypes.any { it == rawSourceType }
        }

    override val isKotlinCollectionType: Boolean
        get() = if (isAlias) {
            false
        } else {
            val rawSourceType = baseSourceType.replace("?", "")
            kotlinCollectionTypes.any { it == rawSourceType }
        }
}
