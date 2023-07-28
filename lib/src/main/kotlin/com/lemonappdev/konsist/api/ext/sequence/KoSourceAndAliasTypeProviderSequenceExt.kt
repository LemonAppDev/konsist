package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with source type of.
 *
 * @return A sequence containing declarations with the source type matching the specified type.
 */
inline fun <reified T> Sequence<KoSourceAndAliasTypeProvider>.withSourceTypeOf(): Sequence<KoSourceAndAliasTypeProvider> =
    withSourceTypeOf(T::class)

/**
 * Sequence containing declarations without source type of.
 *
 * @return A sequence containing declarations without source type matching the specified type.
 */
inline fun <reified T> Sequence<KoSourceAndAliasTypeProvider>.withoutSourceTypeOf(): Sequence<KoSourceAndAliasTypeProvider> =
    withoutSourceTypeOf(T::class)

/**
 * Sequence containing declarations with source type of.
 *
 * @param types The Kotlin classes representing the source types to include.
 * @return A sequence containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withSourceTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations without source type of.
 *
 * @param types The Kotlin classes representing the source types to exclude.
 * @return A sequence containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withoutSourceTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { kClass -> it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations with source type.
 *
 * @param type The source type to include.
 * @param types The source types to include.
 * @return A sequence containing declarations with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withSourceType(type: String, vararg types: String): Sequence<T> = filter {
    it.sourceType == type || types.any { type -> it.sourceType == type }
}

/**
 * Sequence containing declarations without source type.
 *
 * @param type The source type to exclude.
 * @param types The source types to exclude.
 * @return A sequence containing declarations without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withoutSourceType(type: String, vararg types: String): Sequence<T> = filter {
    it.sourceType != type && types.none { type -> it.sourceType == type }
}

/**
 * Sequence containing declarations with alias type of.
 *
 * @return A sequence containing declarations with an alias type matching the specified type.
 */
inline fun <reified T> Sequence<KoSourceAndAliasTypeProvider>.withAliasTypeOf(): Sequence<KoSourceAndAliasTypeProvider> =
    withAliasTypeOf(T::class)

/**
 * Sequence containing declarations without alias type of.
 *
 * @return A sequence containing declarations without an alias type matching the specified type.
 */
inline fun <reified T> Sequence<KoSourceAndAliasTypeProvider>.withoutAliasTypeOf(): Sequence<KoSourceAndAliasTypeProvider> =
    withoutAliasTypeOf(T::class)

/**
 * Sequence containing declarations with alias type of.
 *
 * @param names The Kotlin classes representing the alias type to include.
 * @return A sequence containing declarations with the alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withAliasTypeOf(vararg names: KClass<*>): Sequence<T> = filter {
    names.any { kClass -> it.isAlias && it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations without alias type of.
 *
 * @param names The Kotlin classes representing the alias type to exclude.
 * @return A sequence containing declarations without alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withoutAliasTypeOf(vararg names: KClass<*>): Sequence<T> = filter {
    names.none { kClass -> it.isAlias && it.sourceType == kClass.simpleName }
}

/**
 * Sequence containing declarations with alias type.
 *
 * @param names The alias type names to include.
 * @return A sequence containing declarations with an alias type matching any of the specified names
 * (or any alias type if [names] is empty).
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withAliasType(vararg names: String): Sequence<T> = filter {
    when {
        names.isEmpty() -> it.isAlias
        else -> names.any { name -> it.aliasType == name }
    }
}

/**
 * Sequence containing declarations without alias type.
 *
 * @param names The alias type names to exclude.
 * @return A sequence containing declarations without an alias type matching any of the specified names
 * (or none alias type if [names] is empty).
 */
fun <T : KoSourceAndAliasTypeProvider> Sequence<T>.withoutAliasType(vararg names: String): Sequence<T> = filter {
    when {
        names.isEmpty() -> !it.isAlias
        else -> names.none { name -> it.aliasType == name }
    }
}
