package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with source type of.
 *
 * @param kClass The Kotlin class representing the source type to include.
 * @param kClasses The Kotlin classes representing the source types to include.
 * @return A list containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        val hasMatchingSourceType = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        } else {
            false
        }

        it.sourceType == kClass.simpleName || hasMatchingSourceType
    }

/**
 * List containing declarations without source type of.
 *
 * @param kClass The Kotlin class representing the source type to exclude.
 * @param kClasses The Kotlin classes representing the source types to exclude.
 * @return A list containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        val hasNoMatchingSourceType = if (kClasses.isNotEmpty()) {
            kClasses.none { kClass -> it.sourceType == kClass.simpleName }
        } else {
            true
        }

        it.sourceType != kClass.simpleName && hasNoMatchingSourceType
    }

/**
 * List containing declarations with source type.
 *
 * @param name The source type name to include.
 * @param names The source type name(s) to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceType(name: String, vararg names: String): List<T> = filter {
    it.sourceType == name || names.any { type -> it.sourceType == type }
}

/**
 * List containing declarations without source type.
 *
 * @param name The source type name to exclude.
 * @param names The source type name(s) to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceType(name: String, vararg names: String): List<T> = filter {
    it.sourceType != name && names.none { type -> it.sourceType == type }
}

/**
 * List containing declarations with alias type of.
 *
 * @param kClass The Kotlin class representing the alias type to include
 * @param kClasses The Kotlin classes representing the alias type to include.
 * @return A list containing declarations with the alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withAliasTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val hasMatchingAlias = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        } else {
            false
        }

        it.isAlias && (it.sourceType == kClass.simpleName || hasMatchingAlias)
    }

/**
 * List containing declarations with base source type of.
 *
 * @param kClass The Kotlin class representing the base source type to include.
 * @param kClasses The Kotlin classes representing the base source types to include.
 * @return A list containing declarations with the base source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBaseSourceTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.baseSourceType == kClass.simpleName ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.baseSourceType == kClass.simpleName }
            } else {
                false
            }
    }

/**
 * List containing declarations without base source type of.
 *
 * @param kClass The Kotlin class representing the base source type to exclude.
 * @param kClasses The Kotlin classes representing the base source types to exclude.
 * @return A list containing declarations without base source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBaseSourceTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.baseSourceType != kClass.simpleName &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.baseSourceType == kClass.simpleName }
            } else {
                true
            }
    }

/**
 * List containing declarations with base source type.
 *
 * @param name The base source type name to include.
 * @param names The base source type name(s) to include.
 * @return A list containing declarations with the specified base source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBaseSourceType(name: String, vararg names: String): List<T> =
    filter {
        it.baseSourceType == name || names.any { type -> it.baseSourceType == type }
    }

/**
 * List containing declarations without base source type.
 *
 * @param name The base source type name to exclude.
 * @param names The base source type name(s) to exclude.
 * @return A list containing declarations without specified base source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBaseSourceType(name: String, vararg names: String): List<T> =
    filter {
        it.baseSourceType != name && names.none { type -> it.baseSourceType == type }
    }

/**
 * List containing declarations without alias type of.
 *
 * @param kClass The Kotlin class representing the alias type to exclude.
 * @param kClasses The Kotlin classes representing the alias type to exclude.
 * @return A list containing declarations without alias type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutAliasTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot {
        val hasMatchingAlias = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        } else {
            false
        }

        it.isAlias &&
            (it.sourceType == kClass.simpleName || hasMatchingAlias)
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
        names.isEmpty() -> it.isAlias
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
        names.isEmpty() -> !it.isAlias
        else -> names.none { name -> it.aliasType == name }
    }
}
