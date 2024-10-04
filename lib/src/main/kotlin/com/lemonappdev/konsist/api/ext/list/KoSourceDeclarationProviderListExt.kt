package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import kotlin.reflect.KClass

/**
 * List containing source declarations associated with types.
 *
 * @param predicate A function that defines the condition to be met by the type source declaration.
 *                  If null, all source declarations are included.
 * @return A list of source declarations that match the provided predicate, or all source declarations if no predicate is provided.
 */
fun <T : KoSourceDeclarationProvider> List<T>.sourceDeclarations(
    predicate: ((KoBaseTypeDeclaration) -> Boolean)? = null,
): List<KoBaseTypeDeclaration> =
    filter {
        when (predicate) {
            null -> true
            else -> it.hasSourceDeclaration(predicate)
        }
    }.map { it.sourceDeclaration }

/**
 * List containing source declarations with the specified source declaration.
 *
 * @param predicate The predicate function to determine if a source declaration satisfies a condition.
 * @return A list containing source declarations with the specified source declaration.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.sourceDeclaration) }

/**
 * List containing source declarations without the specified source declaration.
 *
 * @param predicate The predicate function to determine if a source declaration satisfies a condition.
 * @return A list containing source declarations without the specified source declaration.
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclaration(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.sourceDeclaration) }

/**
 * List containing declarations with source declaration of.
 *
 * @param kClass The Kotlin class representing the source declaration to include.
 * @param kClasses The Kotlin class(es) representing the source declaration(s) to include.
 * @return A list containing declarations with the source declaration of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withSourceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with source declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the source declaration(s) to include.
 * @return A list containing declarations with the source declaration of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withSourceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        }
    }

/**
 * List containing declarations without source declaration of.
 *
 * @param kClass The Kotlin class representing the source declaration to exclude.
 * @param kClasses The Kotlin class(es) representing the source declaration(s) to exclude.
 * @return A list containing declarations without source declaration of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutSourceDeclarationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without source declaration of.
 *
 * @param kClasses The Kotlin class(es) representing the source declaration(s) to exclude.
 * @return A list containing declarations without source declaration of the specified Kotlin class(es).
 */
fun <T : KoSourceDeclarationProvider> List<T>.withoutSourceDeclarationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasSourceDeclarationOf(kClass) }
        }
    }
