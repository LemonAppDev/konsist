package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import kotlin.reflect.KClass

/**
 * List containing type declarations.
 */
val <T : KoTypeProvider> List<T>.types: List<KoTypeDeclaration>
    get() = map { it.type }


/**
 * List containing declarations with the specified type.
 *
 * @param predicate The predicate function to determine if a declaration type satisfies a condition.
 * @return A list containing declarations with the specified type.
 */
fun <T : KoTypeProvider> List<T>.withType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.type) }

/**
 * List containing declarations without the specified type.
 *
 * @param predicate The predicate function to determine if a declaration type satisfies a condition.
 * @return A list containing declarations without the specified type.
 */
fun <T : KoTypeProvider> List<T>.withoutType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.type) }

/**
 * List containing declarations with type of.
 *
 * @param kClass The Kotlin class representing the type to include.
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing declarations with the type of the specified Kotlin class(es).
 */
fun <T : KoTypeProvider> List<T>.withTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.hasTypeOf(kClass) ||
                if (kClasses.isNotEmpty()) {
                    kClasses.any { kClass -> it.hasTypeOf(kClass) }
                } else {
                    false
                }
    }

/**
 * List containing declarations without type of.
 *
 * @param kClass The Kotlin class representing the type to exclude.
 * @param kClasses The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing declarations without type of the specified Kotlin class(es).
 */
fun <T : KoTypeProvider> List<T>.withoutTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        it.hasTypeOf(kClass) ||
                if (kClasses.isNotEmpty()) {
                    kClasses.any { kClass -> it.hasTypeOf(kClass) }
                } else {
                    false
                }
    }
