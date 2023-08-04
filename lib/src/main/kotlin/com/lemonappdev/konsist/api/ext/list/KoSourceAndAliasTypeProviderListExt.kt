package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import kotlin.reflect.KClass

/**
 * List containing elements with source type of.
 *
 * @param type The Kotlin class representing the source type to include.
 * @param types The Kotlin classes representing the source types to include.
 * @return A list containing elements with the source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceTypeOf(type: KClass<*>, vararg types: KClass<*>): List<T> =
    filter {
        it.sourceType == type.simpleName ||
            if (types.isNotEmpty()) {
                types.any { kClass -> it.sourceType == kClass.simpleName }
            } else {
                false
            }
    }

/**
 * List containing elements without source type of.
 *
 * @param type The Kotlin class representing the source type to exclude.
 * @param types The Kotlin classes representing the source types to exclude.
 * @return A list containing elements without source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceTypeOf(type: KClass<*>, vararg types: KClass<*>): List<T> =
    filter {
        it.sourceType != type.simpleName &&
            if (types.isNotEmpty()) {
                types.none { kClass -> it.sourceType == kClass.simpleName }
            } else {
                true
            }
    }

/**
 * List containing elements with source type.
 *
 * @param type The source type to include.
 * @param types The source types to include.
 * @return A list containing elements with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceType(type: String, vararg types: String): List<T> = filter {
    it.sourceType == type || types.any { type -> it.sourceType == type }
}

/**
 * List containing elements without source type.
 *
 * @param type The source type to exclude.
 * @param types The source types to exclude.
 * @return A list containing elements without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceType(type: String, vararg types: String): List<T> = filter {
    it.sourceType != type && types.none { type -> it.sourceType == type }
}

/**
 * List containing elements with alias type of.
 *
 * @param name The Kotlin class representing the alias type to include
 * @param names The Kotlin classes representing the alias type to include.
 * @return A list containing elements with the alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withAliasTypeOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.isAlias &&
            (
                it.sourceType == name.simpleName ||
                    if (names.isNotEmpty()) {
                        names.any { kClass -> it.sourceType == kClass.simpleName }
                    } else {
                        false
                    }
                )
    }

/**
 * List containing elements without alias type of.
 *
 * @param name The Kotlin class representing the alias type to exclude.
 * @param names The Kotlin classes representing the alias type to exclude.
 * @return A list containing elements without alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutAliasTypeOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filterNot {
        it.isAlias &&
            (
                it.sourceType == name.simpleName ||
                    if (names.isNotEmpty()) {
                        names.any { kClass -> it.sourceType == kClass.simpleName }
                    } else {
                        false
                    }
                )
    }

/**
 * List containing elements with alias type.
 *
 * @param names The alias type names to include.
 * @return A list containing elements with an alias type matching any of the specified names
 * (or any alias type if [names] is empty).
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withAliasType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.isAlias
        else -> names.any { name -> it.aliasType == name }
    }
}

/**
 * List containing elements without alias type.
 *
 * @param names The alias type names to exclude.
 * @return A list containing elements without an alias type matching any of the specified names
 * (or none alias type if [names] is empty).
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutAliasType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.isAlias
        else -> names.none { name -> it.aliasType == name }
    }
}
