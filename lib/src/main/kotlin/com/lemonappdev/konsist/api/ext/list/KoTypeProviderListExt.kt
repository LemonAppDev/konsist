package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTypeProvider

/**
 * Filters the list to include only declarations that are classes.
 *
 * @return A list containing only class types.
 */
fun <T : KoTypeProvider> List<T>.withClassType(): List<T> = filter { it.isClass }

/**
 * Filters the list to exclude declarations that are classes.
 *
 * @return A list excluding class types.
 */
fun <T : KoTypeProvider> List<T>.withoutClassType(): List<T> = filterNot { it.isClass }

/**
 * Filters the list to include only declarations that are objects.
 *
 * @return A list containing only object types.
 */
fun <T : KoTypeProvider> List<T>.withObjectType(): List<T> = filter { it.isObject }

/**
 * Filters the list to exclude declarations that are objects.
 *
 * @return A list excluding object types.
 */
fun <T : KoTypeProvider> List<T>.withoutObjectType(): List<T> = filterNot { it.isObject }

/**
 * Filters the list to include only declarations that are interfaces.
 *
 * @return A list containing only interface types.
 */
fun <T : KoTypeProvider> List<T>.withInterfaceType(): List<T> = filter { it.isInterface }

/**
 * Filters the list to exclude declarations that are interfaces.
 *
 * @return A list excluding interface types.
 */
fun <T : KoTypeProvider> List<T>.withoutInterfaceType(): List<T> = filterNot { it.isInterface }

/**
 * Filters the list to include only declarations that are type aliases.
 *
 * @return A list containing only type alias.
 */
fun <T : KoTypeProvider> List<T>.withTypeAlias(): List<T> = filter { it.isTypeAlias }

/**
 * Filters the list to exclude declarations that are type aliases.
 *
 * @return A list excluding type aliases.
 */
fun <T : KoTypeProvider> List<T>.withoutTypeAlias(): List<T> = filterNot { it.isTypeAlias }

/**
 * Filters the list to include only declarations that are import aliases.
 * Import aliases refer to imported declarations with an alias.
 *
 * @return A list containing only import alias types.
 */
fun <T : KoTypeProvider> List<T>.withImportAlias(): List<T> = filter { it.isImportAlias }

/**
 * Filters the list to exclude declarations that are import aliases.
 *
 * @return A list excluding import alias types.
 */
fun <T : KoTypeProvider> List<T>.withoutImportAlias(): List<T> = filterNot { it.isImportAlias }

/**
 * List containing declarations that are Kotlin stdlib types.
 * Kotlin stdlib basic types:
 * Byte, Short, Int, Long, Float, Double, UByte, UShort, UInt, ULong, UByteArray, UShortArray, UIntArray, ULongArray,
 * Boolean, Char, String, Array
 *
 * or
 *
 * Kotlin stdlib collection types:
 * AbstractCollection, AbstractIterator, AbstractList, AbstractMap, AbstractMutableCollection, AbstractMutableList,
 * AbstractMutableMap, AbstractMutableSet, AbstractSet, ArrayDeque, ArrayList, Collection, HashMap, HashSet,
 * LinkedHashMap, LinkedHashSet, LinkedList, List, Map, MutableCollection, MutableList, MutableMap, MutableSet, Set,
 *
 * @return A list containing built-in Kotlin types.
 */
fun <T : KoTypeProvider> List<T>.withKotlinType(): List<T> = filter { it.isKotlinType }

/**
 * List containing declarations that are not Kotlin stdlib types:
 * Kotlin stdlib basic types:
 * Byte, Short, Int, Long, Float, Double, UByte, UShort, UInt, ULong, UByteArray, UShortArray, UIntArray, ULongArray,
 * Boolean, Char, String, Array
 *
 * or
 *
 * Kotlin stdlib collection types:
 * AbstractCollection, AbstractIterator, AbstractList, AbstractMap, AbstractMutableCollection, AbstractMutableList,
 * AbstractMutableMap, AbstractMutableSet, AbstractSet, ArrayDeque, ArrayList, Collection, HashMap, HashSet,
 * LinkedHashMap, LinkedHashSet, LinkedList, List, Map, MutableCollection, MutableList, MutableMap, MutableSet, Set,
 *
 * @return A list containing non-built-in Kotlin types.
 */
fun <T : KoTypeProvider> List<T>.withoutKotlinType(): List<T> = filterNot { it.isKotlinType }

/**
 * List containing declarations that are Kotlin stdlib basic types:
 * Byte, Short, Int, Long, Float, Double, UByte, UShort, UInt, ULong, UByteArray, UShortArray, UIntArray, ULongArray,
 * Boolean, Char, String, Array,
 *
 * @return A list containing built-in Kotlin types.
 */
fun <T : KoTypeProvider> List<T>.withKotlinBasicType(): List<T> = filter { it.isKotlinBasicType }

/**
 * List containing declarations that are not Kotlin stdlib basic types:
 * Byte, Short, Int, Long, Float, Double, UByte, UShort, UInt, ULong, UByteArray, UShortArray, UIntArray, ULongArray,
 * Boolean, Char, String, Array,
 *
 *  @return A list containing non-built-in Kotlin types.
 */
fun <T : KoTypeProvider> List<T>.withoutKotlinBasicType(): List<T> = filterNot { it.isKotlinBasicType }

/**
 * List containing declarations that are Kotlin stdlib collection types:
 * AbstractCollection, AbstractIterator, AbstractList, AbstractMap, AbstractMutableCollection, AbstractMutableList,
 * AbstractMutableMap, AbstractMutableSet, AbstractSet, ArrayDeque, ArrayList, Collection, HashMap, HashSet,
 * LinkedHashMap, LinkedHashSet, LinkedList, List, Map, MutableCollection, MutableList, MutableMap, MutableSet, Set,
 *
 * @return A list containing built-in Kotlin types.
 */
fun <T : KoTypeProvider> List<T>.withKotlinCollectionType(): List<T> = filter { it.isKotlinCollectionType }

/**
 * List containing declarations that are not Kotlin stdlib collection types:
 * AbstractCollection, AbstractIterator, AbstractList, AbstractMap, AbstractMutableCollection, AbstractMutableList,
 * AbstractMutableMap, AbstractMutableSet, AbstractSet, ArrayDeque, ArrayList, Collection, HashMap, HashSet,
 * LinkedHashMap, LinkedHashSet, LinkedList, List, Map, MutableCollection, MutableList, MutableMap, MutableSet, Set,
 *
 * @return A list containing non-built-in Kotlin types.
 */
fun <T : KoTypeProvider> List<T>.withoutKotlinCollectionType(): List<T> = filterNot { it.isKotlinCollectionType }

/**
 * Filters the list to include only declarations that are recognized as function types.
 * Function types refer to declarations that define a function signature.
 *
 * @return A list containing only function types.
 */
fun <T : KoTypeProvider> List<T>.withFunctionType(): List<T> = filter { it.isFunctionType }

/**
 * Filters the list to exclude declarations that are recognized as function types.
 *
 * @return A list excluding function types.
 */
fun <T : KoTypeProvider> List<T>.withoutFunctionType(): List<T> = filterNot { it.isFunctionType }

/**
 * Filters the list to include only declarations that are type parameters.
 * Type parameters are placeholders for types that are specified when a generic class or method is instantiated.
 *
 * @return A list containing only type parameters.
 */
fun <T : KoTypeProvider> List<T>.withTypeParameter(): List<T> = filter { it.isTypeParameter }

/**
 * Filters the list to exclude declarations that are type parameters.
 *
 * @return A list excluding type parameters.
 */
fun <T : KoTypeProvider> List<T>.withoutTypeParameter(): List<T> = filterNot { it.isTypeParameter }

/**
 * Filters the list to include only declarations that are star projections.
 * Star projections represent wildcard types in Kotlin (e.g., `*` in generics).
 *
 * @return A list containing only star projections.
 */
fun <T : KoTypeProvider> List<T>.withStarProjection(): List<T> = filter { it.isStarProjection }

/**
 * Filters the list to exclude declarations that are star projections.
 *
 * @return A list excluding star projections.
 */
fun <T : KoTypeProvider> List<T>.withoutStarProjection(): List<T> = filterNot { it.isStarProjection }

/**
 * Filters the list to include only declarations that are external types.
 * External types are those defined outside the project's codebase, typically in external libraries.
 *
 * @return A list containing only external types.
 */
fun <T : KoTypeProvider> List<T>.withExternalType(): List<T> = filter { it.isExternalType }

/**
 * Filters the list to exclude declarations that are external types.
 *
 * @return A list excluding external types.
 */
fun <T : KoTypeProvider> List<T>.withoutExternalType(): List<T> = filterNot { it.isExternalType }
