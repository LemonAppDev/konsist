package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider

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
fun <T : KoKotlinTypeProvider> List<T>.withKotlinType(): List<T> = filter { it.isKotlinType }

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
fun <T : KoKotlinTypeProvider> List<T>.withoutKotlinType(): List<T> = filterNot { it.isKotlinType }

/**
 * List containing declarations that are Kotlin stdlib basic types:
 * Byte, Short, Int, Long, Float, Double, UByte, UShort, UInt, ULong, UByteArray, UShortArray, UIntArray, ULongArray,
 * Boolean, Char, String, Array,
 *
 * @return A list containing built-in Kotlin types.
 */
fun <T : KoKotlinTypeProvider> List<T>.withKotlinBasicType(): List<T> = filter { it.isKotlinBasicType }

/**
 * List containing declarations that are not Kotlin stdlib basic types:
 * Byte, Short, Int, Long, Float, Double, UByte, UShort, UInt, ULong, UByteArray, UShortArray, UIntArray, ULongArray,
 * Boolean, Char, String, Array,
 *
 *  @return A list containing non-built-in Kotlin types.
 */
fun <T : KoKotlinTypeProvider> List<T>.withoutKotlinBasicType(): List<T> = filterNot { it.isKotlinBasicType }

/**
 * List containing declarations that are Kotlin stdlib collection types:
 * AbstractCollection, AbstractIterator, AbstractList, AbstractMap, AbstractMutableCollection, AbstractMutableList,
 * AbstractMutableMap, AbstractMutableSet, AbstractSet, ArrayDeque, ArrayList, Collection, HashMap, HashSet,
 * LinkedHashMap, LinkedHashSet, LinkedList, List, Map, MutableCollection, MutableList, MutableMap, MutableSet, Set,
 *
 * @return A list containing built-in Kotlin types.
 */
fun <T : KoKotlinTypeProvider> List<T>.withKotlinCollectionType(): List<T> = filter { it.isKotlinCollectionType }

/**
 * List containing declarations that are not Kotlin stdlib collection types:
 * AbstractCollection, AbstractIterator, AbstractList, AbstractMap, AbstractMutableCollection, AbstractMutableList,
 * AbstractMutableMap, AbstractMutableSet, AbstractSet, ArrayDeque, ArrayList, Collection, HashMap, HashSet,
 * LinkedHashMap, LinkedHashSet, LinkedList, List, Map, MutableCollection, MutableList, MutableMap, MutableSet, Set,
 *
 * @return A list containing non-built-in Kotlin types.
 */
fun <T : KoKotlinTypeProvider> List<T>.withoutKotlinCollectionType(): List<T> = filterNot { it.isKotlinCollectionType }
