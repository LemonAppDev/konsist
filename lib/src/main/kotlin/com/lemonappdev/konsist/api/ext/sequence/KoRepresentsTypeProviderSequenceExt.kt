package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that represents the type.
 *
 * @param type The type to include.
 * @param types The types to include.
 * @return A sequence containing declarations with the specified types.
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withRepresentedType(type: String, vararg types: String): Sequence<T> = filter {
    it.representsType(type) || types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that do not represent the type.
 *
 * @param type The type to exclude.
 * @param types The types to exclude.
 * @return A sequence containing declarations without the specified types.
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withoutRepresentedType(type: String, vararg types: String): Sequence<T> =
    filter {
        !it.representsType(type) && types.none { type -> it.representsType(type) }
    }

/**
 * Sequence containing all declarations that represents the type.
 *
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing declarations with types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withRepresentedTypeOf(vararg types: KClass<*>): Sequence<T> =
    filter {
        types.any { type ->
            type
                .qualifiedName
                ?.let { name -> it.representsType(name) }
                ?: false
        }
    }

/**
 * Sequence containing all declarations that do not represent the type.
 *
 * @param types The Kotlin classes representing the types to exclude.
 * @return A sequence containing declarations without types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> Sequence<T>.withoutRepresentedTypeOf(vararg types: KClass<*>): Sequence<T> =
    filter {
        types.none { type ->
            type
                .qualifiedName
                ?.let { name -> it.representsType(name) }
                ?: false
        }
    }

/**
 * Sequence containing all declarations that represents the type.
 *
 * @return A sequence containing declarations with types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoRepresentsTypeProvider>.withRepresentedTypeOf(): Sequence<KoRepresentsTypeProvider> =
    withRepresentedTypeOf(T::class)

/**
 * Sequence containing all declarations that do not represent the type.
 *
 * @return A sequence containing declarations without types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoRepresentsTypeProvider>.withoutRepresentedTypeOf(): Sequence<KoRepresentsTypeProvider> =
    withoutRepresentedTypeOf(T::class)
