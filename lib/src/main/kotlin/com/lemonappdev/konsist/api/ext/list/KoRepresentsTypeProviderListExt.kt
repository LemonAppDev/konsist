package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import kotlin.reflect.KClass

/**
 * List containing all declarations that represents the type.
 *
 * @param type The type to include.
 * @param types The types to include.
 * @return A list containing declarations with the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedType(type: String, vararg types: String): List<T> = filter {
    it.representsType(type) || types.any { type -> it.representsType(type) }
}

/**
 * List containing all declarations that do not represent the type.
 *
 * @param type The type to exclude.
 * @param types The types to exclude.
 * @return A list containing declarations without the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedType(type: String, vararg types: String): List<T> =
    filter {
        !it.representsType(type) && types.none { type -> it.representsType(type) }
    }

/**
 * List containing all declarations that represents the type.
 *
 * @param types The Kotlin classes representing the types to include.
 * @return A list containing declarations with types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedTypeOf(vararg types: KClass<*>): List<T> =
    filter {
        types.any { type ->
            type
                .qualifiedName
                ?.let { name -> it.representsType(name) }
                ?: false
        }
    }

/**
 * List containing all declarations that do not represent the type.
 *
 * @param types The Kotlin classes representing the types to exclude.
 * @return A list containing declarations without types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedTypeOf(vararg types: KClass<*>): List<T> =
    filter {
        types.none { type ->
            type
                .qualifiedName
                ?.let { name -> it.representsType(name) }
                ?: false
        }
    }

/**
 * List containing all declarations that represents the type.
 *
 * @return A list containing declarations with types matching the specified reified type parameter.
 */
inline fun <reified T> List<KoRepresentsTypeProvider>.withRepresentedTypeOf(): List<KoRepresentsTypeProvider> =
    withRepresentedTypeOf(T::class)

/**
 * List containing all declarations that do not represent the type.
 *
 * @return A list containing declarations without types matching the specified reified type parameter.
 */
inline fun <reified T> List<KoRepresentsTypeProvider>.withoutRepresentedTypeOf(): List<KoRepresentsTypeProvider> =
    withoutRepresentedTypeOf(T::class)
