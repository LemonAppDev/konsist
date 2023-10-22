package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations that represents the type.
 *
 * @param name The type name to include.
 * @param names The type name(s) to include.
 * @return A list containing declarations with the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedType(name: String?, vararg names: String?): List<T> = filter {
    it.representsType(name) || names.any { type -> it.representsType(type) }
}

/**
 * List containing declarations that do not represent the type.
 *
 * @param name The type name to exclude.
 * @param names The type name(s) to exclude.
 * @return A list containing declarations without the specified types.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedType(name: String?, vararg names: String?): List<T> =
    filter {
        !it.representsType(name) && names.none { type -> it.representsType(type) }
    }

/**
 * List containing declarations that represents the type.
 *
 * @param kClass The Kotlin class representing the type to include.
 * @param kClasses The Kotlin classes representing the types to include.
 * @return A list containing declarations with types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withRepresentedTypeOf(kClass: KClass<*>?, vararg kClasses: KClass<*>?): List<T> =
    filter {
        it.representsType(kClass?.qualifiedName) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { type ->
                    it.representsType(type?.qualifiedName)
                }
            } else {
                false
            }
    }

/**
 * List containing declarations that do not represent the type.
 *
 * @param kClass The Kotlin class representing the type to exclude.
 * @param kClasses The Kotlin classes representing the types to exclude.
 * @return A list containing declarations without types matching the specified Kotlin classes.
 */
fun <T : KoRepresentsTypeProvider> List<T>.withoutRepresentedTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>?): List<T> =
    filter {
        !it.representsType(kClass.qualifiedName) &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { type ->
                    it.representsType(type?.qualifiedName)
                }
            } else {
                true
            }
    }
