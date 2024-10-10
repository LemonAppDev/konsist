package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import kotlin.reflect.KClass

/**
 * List of source declarations for each declaration in the list.
 */
val <T : KoSourceDeclarationProvider> List<T>.sourceDeclarations: List<KoBaseDeclaration>
    get() = map { it.sourceDeclaration }

/**
 * Filters the list, returning only elements whose source declarations match the given [predicate].
 *
 * @param predicate A function that returns `true` for source declarations to include in the result.
 * @return A list of elements whose source declarations satisfy the [predicate].
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclaration(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filter { predicate(it.sourceDeclaration) }

/**
 * Filters the list, returning only elements whose source declarations do not match the given [predicate].
 *
 * @param predicate A function that returns `true` for source declarations to exclude from the result.
 * @return A list of elements whose source declarations do not satisfy the [predicate].
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclaration(predicate: (KoBaseDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.sourceDeclaration) }

/**
 * Filters the list, returning elements whose source declarations match any of the provided classes.
 *
 * @param kClass The primary class to match source declarations against.
 * @param kClasses Additional classes to match source declarations against.
 * @return A list of elements whose source declarations match any of the provided classes.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withSourceDeclarationOf(listOf(kClass, *kClasses))

/**
 * Filters the list, returning elements whose source declarations match any class in [kClasses].
 *
 * @param kClasses A collection of classes to match source declarations against.
 * @return A list of elements whose source declarations match any class in [kClasses].
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        }
    }

/**
 * Filters the list, returning elements whose source declarations do not match any of the provided classes.
 *
 * @param kClass The primary class to exclude source declarations from.
 * @param kClasses Additional classes to exclude source declarations from.
 * @return A list of elements whose source declarations do not match any of the provided classes.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutSourceDeclarationOf(listOf(kClass, *kClasses))

/**
 * Filters the list, returning elements whose source declarations do not match any class in [kClasses].
 *
 * @param kClasses A collection of classes to exclude source declarations from.
 * @return A list of elements whose source declarations do not match any class in [kClasses].
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        }
    }
