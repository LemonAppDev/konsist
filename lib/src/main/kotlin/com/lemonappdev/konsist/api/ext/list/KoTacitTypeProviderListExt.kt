package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with tacit type.
 *
 * @param type The tacit type to include.
 * @param types The tacit type(s) to include.
 * @return A list containing declarations with the specified tacit types.
 */
fun <T : KoTacitTypeProvider> List<T>.withTacitType(type: String, vararg types: String): List<T> = filter {
    it.hasTacitType(type) || types.any { type -> it.hasTacitType(type) }
}

/**
 * List containing declarations without tacit type.
 *
 * @param type The tacit type to exclude.
 * @param types The tacit type(s) to exclude.
 * @return A list containing declarations without specified tacit types.
 */
fun <T : KoTacitTypeProvider> List<T>.withoutTacitType(type: String, vararg types: String): List<T> = filter {
    !it.hasTacitType(type) && types.none { type -> it.hasTacitType(type) }
}

/**
 * List containing declarations with tacit type.
 *
 * @param kClass The Kotlin class representing the tacit type to include.
 * @param kClasses The Kotlin class(es) representing the tacit type(s) to include.
 * @return A list containing declarations with the tacit type of the specified Kotlin class(es).
 */
fun <T : KoTacitTypeProvider> List<T>.withTacitTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val missesAtLeastOneTacitType = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasTacitTypeOf(kClass) }
        } else {
            false
        }

        it.hasTacitTypeOf(kClass) || missesAtLeastOneTacitType
    }

/**
 * List containing declarations without tacit type.
 *
 * @param kClass The Kotlin class representing the tacit type to exclude.
 * @param kClasses The Kotlin class(es) representing the tacit type(s) to exclude.
 * @return A list containing declarations without tacit type of the specified Kotlin class(es).
 */
fun <T : KoTacitTypeProvider> List<T>.withoutTacitTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        val missesAtLeastOneTacitType = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.hasTacitTypeOf(kClass) }
        } else {
            false
        }

        it.hasTacitTypeOf(kClass) || missesAtLeastOneTacitType
    }
