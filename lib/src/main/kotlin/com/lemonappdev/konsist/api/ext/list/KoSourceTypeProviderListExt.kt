package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with source type of.
 *
 * @param kClass The Kotlin class representing the source type to include.
 * @param kClasses The Kotlin classes representing the source types to include.
 * @return A list containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withSourceTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with source type of.
 *
 * @param kClasses The Kotlin classes representing the source types to include.
 * @return A list containing declarations with the source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withSourceTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations without source type of.
 *
 * @param kClass The Kotlin class representing the source type to exclude.
 * @param kClasses The Kotlin classes representing the source types to exclude.
 * @return A list containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutSourceTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without source type of.
 *
 * @param kClasses The Kotlin classes representing the source types to exclude.
 * @return A list containing declarations without source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutSourceTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.sourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations with source type.
 *
 * @param name The source type name to include.
 * @param names The source type name(s) to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withSourceType(
    name: String,
    vararg names: String,
): List<T> = withSourceType(listOf(name, *names))

/**
 * List containing declarations with source type.
 *
 * @param names The source type name(s) to include.
 * @return A list containing declarations with the specified source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withSourceType(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.sourceType == type }
        }
    }

/**
 * List containing declarations without source type.
 *
 * @param name The source type name to exclude.
 * @param names The source type name(s) to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutSourceType(
    name: String,
    vararg names: String,
): List<T> = withoutSourceType(listOf(name, *names))

/**
 * List containing declarations without source type.
 *
 * @param names The source type name(s) to exclude.
 * @return A list containing declarations without specified source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutSourceType(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.sourceType == type }
        }
    }

/**
 * List containing declarations with bare source type of.
 *
 * @param kClass The Kotlin class representing the bare source type to include.
 * @param kClasses The Kotlin classes representing the bare source types to include.
 * @return A list containing declarations with the bare source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withBareSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withBareSourceTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with bare source type of.
 *
 * @param kClasses The Kotlin classes representing the bare source types to include.
 * @return A list containing declarations with the bare source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withBareSourceTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.bareSourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations without bare source type of.
 *
 * @param kClass The Kotlin class representing the bare source type to exclude.
 * @param kClasses The Kotlin classes representing the bare source types to exclude.
 * @return A list containing declarations without bare source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutBareSourceTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutBareSourceTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without bare source type of.
 *
 * @param kClasses The Kotlin classes representing the bare source types to exclude.
 * @return A list containing declarations without bare source type matching any of the specified types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutBareSourceTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.bareSourceType == kClass.simpleName }
        }
    }

/**
 * List containing declarations with base source type.
 *
 * @param name The bare source type name to include.
 * @param names The bare source type name(s) to include.
 * @return A list containing declarations with the specified bare source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withBareSourceType(
    name: String,
    vararg names: String,
): List<T> = withBareSourceType(listOf(name, *names))

/**
 * List containing declarations with base source type.
 *
 * @param names The bare source type name(s) to include.
 * @return A list containing declarations with the specified bare source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withBareSourceType(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.bareSourceType == type }
        }
    }

/**
 * List containing declarations without bare source type.
 *
 * @param name The bare source type name to exclude.
 * @param names The bare source type name(s) to exclude.
 * @return A list containing declarations without specified base source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutBareSourceType(
    name: String,
    vararg names: String,
): List<T> = withoutBareSourceType(listOf(name, *names))

/**
 * List containing declarations without bare source type.
 *
 * @param names The bare source type name(s) to exclude.
 * @return A list containing declarations without specified base source types.
 */
fun <T : KoSourceTypeProvider> List<T>.withoutBareSourceType(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.bareSourceType == type }
        }
    }
