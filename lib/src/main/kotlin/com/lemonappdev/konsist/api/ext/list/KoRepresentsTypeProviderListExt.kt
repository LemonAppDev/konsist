package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import kotlin.reflect.KClass

/**
 * List containing elements that represents the type.
 *
 * @param type The type to include.
 * @param types The types to include.
 * @return A list containing elements with the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedType(type: String, vararg types: String): List<T> = filter {
    it.representsType(type) || types.any { type -> it.representsType(type) }
}

/**
 * List containing elements that do not represent the type.
 *
 * @param type The type to exclude.
 * @param types The types to exclude.
 * @return A list containing elements without the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedType(type: String, vararg types: String): List<T> =
    filter {
        !it.representsType(type) && types.none { type -> it.representsType(type) }
    }

/**
 * List containing elements that represents the type.
 *
 * @param type The Kotlin class representing the type to include.
 * @param types The Kotlin classes representing the types to include.
 * @return A list containing elements with types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedTypeOf(type: KClass<*>, vararg types: KClass<*>): List<T> =
    filter {
        type.qualifiedName?.let { type -> it.representsType(type) } ?: false ||
            if (types.isNotEmpty()) {
                types.any { type ->
                    type
                        .qualifiedName
                        ?.let { name -> it.representsType(name) }
                        ?: false
                }
            } else {
                false
            }
    }

/**
 * List containing elements that do not represent the type.
 *
 * @param type The Kotlin class representing the type to exclude.
 * @param types The Kotlin classes representing the types to exclude.
 * @return A list containing elements without types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedTypeOf(type: KClass<*>, vararg types: KClass<*>): List<T> =
    filter {
        type.qualifiedName?.let { type -> !it.representsType(type) } ?: true &&
            if (types.isNotEmpty()) {
                types.none { type ->
                    type
                        .qualifiedName
                        ?.let { name -> it.representsType(name) }
                        ?: false
                }
            } else {
                true
            }
    }
