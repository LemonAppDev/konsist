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
        val hasMatchingSourceType =
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.sourceType == kClass.simpleName }
            } else {
                false
            }

        it.sourceType == kClass.simpleName || hasMatchingSourceType
    }

/**
 * List containing declarations with source type of.
 *
 * @param kClasses The Kotlin classes representing the source types to include.
 * @return A list containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceTypeOf(kClasses: Set<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations with source type of.
 *
 * @param kClasses The Kotlin classes representing the source types to include.
 * @return A list containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceTypeOf(kClasses: List<KClass<*>>): List<T> = withSourceTypeOf(kClasses.toSet())

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
        val hasNoMatchingSourceType =
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.sourceType == kClass.simpleName }
            } else {
                true
            }

        it.sourceType != kClass.simpleName && hasNoMatchingSourceType
    }

/**
 * List containing declarations without source type of.
 *
 * @param kClasses The Kotlin classes representing the source types to exclude.
 * @return A list containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceTypeOf(kClasses: Set<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations without source type of.
 *
 * @param kClasses The Kotlin classes representing the source types to exclude.
 * @return A list containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceTypeOf(kClasses: List<KClass<*>>): List<T> =
    withoutSourceTypeOf(kClasses.toSet())

/**
 * List containing declarations with source type.
 *
 * @param name The source type name to include.
 * @param names The source type name(s) to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceType(
    name: String,
    vararg names: String,
): List<T> = filter { it.sourceType == name || names.any { type -> it.sourceType == type } }

/**
 * List containing declarations with source type.
 *
 * @param names The source type name(s) to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceType(names: Set<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.sourceType == type }
        }
    }

/**
 * List containing declarations with source type.
 *
 * @param names The source type name(s) to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withSourceType(names: List<String>): List<T> = withSourceType(names.toSet())

/**
 * List containing declarations without source type.
 *
 * @param name The source type name to exclude.
 * @param names The source type name(s) to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceType(
    name: String,
    vararg names: String,
): List<T> = filter { it.sourceType != name && names.none { type -> it.sourceType == type } }

/**
 * List containing declarations without source type.
 *
 * @param names The source type name(s) to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceType(names: Set<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.sourceType == type }
        }
    }

/**
 * List containing declarations without source type.
 *
 * @param names The source type name(s) to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutSourceType(names: List<String>): List<T> = withoutSourceType(names.toSet())

/**
 * List containing declarations with bare source type of.
 *
 * @param kClass The Kotlin class representing the bare source type to include.
 * @param kClasses The Kotlin classes representing the bare source types to include.
 * @return A list containing declarations with the bare source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBareSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        it.bareSourceType == kClass.simpleName ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.bareSourceType == kClass.simpleName }
            } else {
                false
            }
    }

/**
 * List containing declarations with bare source type of.
 *
 * @param kClasses The Kotlin classes representing the bare source types to include.
 * @return A list containing declarations with the bare source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBareSourceTypeOf(kClasses: Set<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.bareSourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations with bare source type of.
 *
 * @param kClasses The Kotlin classes representing the bare source types to include.
 * @return A list containing declarations with the bare source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBareSourceTypeOf(kClasses: List<KClass<*>>): List<T> =
    withBareSourceTypeOf(kClasses.toSet())

/**
 * List containing declarations without bare source type of.
 *
 * @param kClass The Kotlin class representing the bare source type to exclude.
 * @param kClasses The Kotlin classes representing the bare source types to exclude.
 * @return A list containing declarations without bare source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBareSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        it.bareSourceType != kClass.simpleName &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.bareSourceType == kClass.simpleName }
            } else {
                true
            }
    }

/**
 * List containing declarations without bare source type of.
 *
 * @param kClasses The Kotlin classes representing the bare source types to exclude.
 * @return A list containing declarations without bare source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBareSourceTypeOf(kClasses: Set<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.bareSourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations without bare source type of.
 *
 * @param kClasses The Kotlin classes representing the bare source types to exclude.
 * @return A list containing declarations without bare source type matching any of the specified types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBareSourceTypeOf(kClasses: List<KClass<*>>): List<T> =
    withoutBareSourceTypeOf(kClasses.toSet())

/**
 * List containing declarations with base source type.
 *
 * @param name The bare source type name to include.
 * @param names The bare source type name(s) to include.
 * @return A list containing declarations with the specified bare source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBareSourceType(
    name: String,
    vararg names: String,
): List<T> = filter { it.bareSourceType == name || names.any { type -> it.bareSourceType == type } }

/**
 * List containing declarations with base source type.
 *
 * @param names The bare source type name(s) to include.
 * @return A list containing declarations with the specified bare source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBareSourceType(names: Set<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.bareSourceType == type }
        }
    }

/**
 * List containing declarations with base source type.
 *
 * @param names The bare source type name(s) to include.
 * @return A list containing declarations with the specified bare source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withBareSourceType(names: List<String>): List<T> = withBareSourceType(names.toSet())

/**
 * List containing declarations without bare source type.
 *
 * @param name The bare source type name to exclude.
 * @param names The bare source type name(s) to exclude.
 * @return A list containing declarations without specified base source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBareSourceType(
    name: String,
    vararg names: String,
): List<T> = filter { it.bareSourceType != name && names.none { type -> it.bareSourceType == type } }

/**
 * List containing declarations without bare source type.
 *
 * @param names The bare source type name(s) to exclude.
 * @return A list containing declarations without specified base source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBareSourceType(names: Set<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.bareSourceType == type }
        }
    }

/**
 * List containing declarations without bare source type.
 *
 * @param names The bare source type name(s) to exclude.
 * @return A list containing declarations without specified base source types.
 */
fun <T : KoSourceAndAliasTypeProvider> List<T>.withoutBareSourceType(names: List<String>): List<T> = withoutBareSourceType(names.toSet())
