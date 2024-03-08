package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

/**
 * List containing declarations of all types.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing all declarations.
 */
fun <T : KoDeclarationProvider> List<T>.declarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoBaseDeclaration> = flatMap { it.declarations(includeNested, includeLocal) }

/**
 * List containing declarations with any declaration.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing declarations with any declaration.
 */
fun <T : KoDeclarationProvider> List<T>.withDeclarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter { it.hasDeclarations(includeNested, includeLocal) }

/**
 * List containing declarations with no declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing declarations with no declarations.
 */
fun <T : KoDeclarationProvider> List<T>.withoutDeclarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot { it.hasDeclarations(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one declaration satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @param predicate A function that defines the condition to be met by a declaration.
 * @return A list containing declarations with at least one declaration satisfying the predicate.
 */
fun <T : KoDeclarationProvider> List<T>.withDeclaration(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoBaseDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasDeclaration(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that not have declaration satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @param predicate A function that defines the condition to be met by a declaration.
 * @return A list containing declarations without declaration satisfying the provided predicate.
 */
fun <T : KoDeclarationProvider> List<T>.withoutDeclaration(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoBaseDeclaration) -> Boolean,
): List<T> = filterNot { it.hasDeclaration(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all declarations satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @param predicate A function that defines the condition to be met by all declarations.
 * @return A filtered list containing declarations with all declarations satisfying the predicate.
 */
fun <T : KoDeclarationProvider> List<T>.withAllDeclarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoBaseDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllDeclarations(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one declaration not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @param predicate A function that defines the condition to be met by all declarations.
 * @return A list containing declarations that have at least one declaration not satisfying the provided predicate.
 */
fun <T : KoDeclarationProvider> List<T>.withoutAllDeclarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoBaseDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllDeclarations(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @param predicate A function that defines the condition to be met by the list of declarations.
 * @return A list containing declarations with declarations satisfying the predicate.
 */
fun <T : KoDeclarationProvider> List<T>.withDeclarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoBaseDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.declarations(includeNested, includeLocal)) }

/**
 * List containing declarations without declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @param predicate A function that defines the condition to be met by the list of declarations.
 * @return A list containing declarations without declarations satisfying the predicate.
 */
fun <T : KoDeclarationProvider> List<T>.withoutDeclarations(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoBaseDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.declarations(includeNested, includeLocal)) }
