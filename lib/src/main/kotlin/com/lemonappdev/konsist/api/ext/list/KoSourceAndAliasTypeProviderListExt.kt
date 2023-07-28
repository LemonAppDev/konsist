package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with source type of.
 *
 * @return A list containing declarations with the source type matching the specified type.
 */
inline fun <reified T> List<KoSourceAndAliasTypeProvider>.withSourceTypeOf(): List<KoSourceAndAliasTypeProvider> =
    withSourceTypeOf(T::class)

/**
 * List containing declarations without source type of.
 *
 * @return A list containing declarations without source type matching the specified type.
 */
inline fun <reified T> List<KoSourceAndAliasTypeProvider>.withoutSourceTypeOf(): List<KoSourceAndAliasTypeProvider> =
    withoutSourceTypeOf(T::class)

/**
 * List containing declarations with source type of.
 *
 * @param types The Kotlin classes representing the source types to include.
 * @return A list containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.any { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * List containing declarations without source type of.
 *
 * @param types The Kotlin classes representing the source types to exclude.
 * @return A list containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.none { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * List containing declarations with source type.
 *
 * @param type The source type to include.
 * @param types The source types to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceType(type: String, vararg types: String): List<T> = filter {
    it.sourceType == type || types.any { type -> it.sourceType == type }
}

/**
 * List containing declarations without source type.
 *
 * @param type The source type to exclude.
 * @param types The source types to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceType(type: String, vararg types: String): List<T> = filter {
    it.sourceType != type && types.none { type -> it.sourceType == type }
}

/**
 * List containing declarations with alias type of.
 *
 * @return A list containing declarations with an alias type matching the specified type.
 */
inline fun <reified T> List<KoSourceAndAliasTypeProvider>.withAliasTypeOf(): List<KoSourceAndAliasTypeProvider> =
    withAliasTypeOf(T::class)

/**
 * List containing declarations without alias type of.
 *
 * @return A list containing declarations without an alias type matching the specified type.
 */
inline fun <reified T> List<KoSourceAndAliasTypeProvider>.withoutAliasTypeOf(): List<KoSourceAndAliasTypeProvider> =
    withoutAliasTypeOf(T::class)

/**
 * List containing declarations with alias type of.
 *
 * @param names The Kotlin classes representing the alias type to include.
 * @return A list containing declarations with the alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withAliasTypeOf(vararg names: KClass<*>): List<T> = filter {
    names.any { kClass -> it.isAlias() && it.sourceType == kClass.simpleName }
}

/**
 * List containing declarations without alias type of.
 *
 * @param names The Kotlin classes representing the alias type to exclude.
 * @return A list containing declarations without alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutAliasTypeOf(vararg names: KClass<*>): List<T> = filter {
    names.none { kClass -> it.isAlias() && it.sourceType == kClass.simpleName }
}

/**
 * List containing declarations with alias type.
 *
 * @param names The alias type names to include.
 * @return A list containing declarations with an alias type matching any of the specified names
 * (or any alias type if [names] is empty).
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withAliasType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.isAlias()
        else -> names.any { name -> it.aliasType == name }
    }
}

/**
 * List containing declarations without alias type.
 *
 * @param names The alias type names to exclude.
 * @return A list containing declarations without an alias type matching any of the specified names
 * (or none alias type if [names] is empty).
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutAliasType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.isAlias()
        else -> names.none { name -> it.aliasType == name }
    }
}
