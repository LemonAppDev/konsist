package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations that represents the type.
 *
 * @param name The type name to include.
 * @param names The type name(s) to include.
 * @return A list containing declarations with the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedType(
    name: String?,
    vararg names: String?,
): List<T> = withRepresentedType(listOf(name, *names))

/**
 * List containing declarations that represents the type.
 *
 * @param names The type name(s) to include.
 * @return A list containing declarations with the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedType(names: Collection<String?>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { type -> it.representsType(type) }
        }
    }

/**
 * List containing declarations that do not represent the type.
 *
 * @param name The type name to exclude.
 * @param names The type name(s) to exclude.
 * @return A list containing declarations without the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedType(
    name: String?,
    vararg names: String?,
): List<T> = withoutRepresentedType(listOf(name, *names))

/**
 * List containing declarations that do not represent the type.
 *
 * @param names The type name(s) to exclude.
 * @return A list containing declarations without the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedType(names: Collection<String?>): List<T> =
    filter {
        when {
            names.isEmpty() -> false
            else -> names.none { type -> it.representsType(type) }
        }
    }

/**
 * List containing declarations that represents the type.
 *
 * @param kClass The Kotlin class representing the type to include.
 * @param kClasses The Kotlin classes representing the types to include.
 * @return A list containing declarations with types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedTypeOf(
    kClass: KClass<*>?,
    vararg kClasses: KClass<*>?,
): List<T> = withRepresentedTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations that represents the type.
 *
 * @param kClasses The Kotlin classes representing the types to include.
 * @return A list containing declarations with types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedTypeOf(kClasses: Collection<KClass<*>?>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { type -> it.representsType(type?.qualifiedName) }
        }
    }

/**
 * List containing declarations that do not represent the type.
 *
 * @param kClass The Kotlin class representing the type to exclude.
 * @param kClasses The Kotlin classes representing the types to exclude.
 * @return A list containing declarations without types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedTypeOf(
    kClass: KClass<*>?,
    vararg kClasses: KClass<*>?,
): List<T> = withoutRepresentedTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations that do not represent the type.
 *
 * @param kClasses The Kotlin classes representing the types to exclude.
 * @return A list containing declarations without types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedTypeOf(kClasses: Collection<KClass<*>?>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> false
            else -> kClasses.none { type -> it.representsType(type?.qualifiedName) }
        }
    }
