package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

/**
 * List containing declarations of all types.
 *
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing all declarations.
 */
fun <T : KoDeclarationProvider> List<T>.declarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<KoBaseDeclaration> = flatMap { it.declarations(includeNested, includeLocal) }

/**
 * List containing elements with any declaration.
 *
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing elements with any declaration.
 */
fun <T : KoDeclarationProvider> List<T>.withDeclarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> = filter { it.declarations(includeNested, includeLocal).isNotEmpty() }

/**
 * List containing elements with all specified declarations.
 *
 * @param name The name of the declaration to include.
 * @param names The name(s) of the declaration(s) to include.
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing elements with all specified declaration(s).
 */
fun <T : KoDeclarationProvider> List<T>.withAllDeclarations(
    name: String,
    vararg names: String,
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> = filter {
    it.containsDeclaration(name, includeNested, includeLocal) &&
        if (names.isNotEmpty()) {
            names.all { name -> it.containsDeclaration(name, includeNested, includeLocal) }
        } else {
            true
        }
}

/**
 * List containing elements with some declarations.
 *
 * @param name The name of the declaration to include.
 * @param names The names of the declarations to include.
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing elements with at least one of the specified declaration(s).
 */
fun <T : KoDeclarationProvider> List<T>.withSomeDeclarations(
    name: String,
    vararg names: String,
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> = filter {
    it.containsDeclaration(name, includeNested, includeLocal) || names.any { name ->
        it.containsDeclaration(
            name,
            includeNested,
            includeLocal,
        )
    }
}

/**
 * List containing elements without any declaration.
 *
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing elements with no declaration.
 */
fun <T : KoDeclarationProvider> List<T>.withoutDeclarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> = filter { it.declarations(includeNested, includeLocal).isEmpty() }

/**
 * List containing elements without all specified declarations.
 *
 * @param name The name of the declaration to exclude.
 * @param names The name(s) of the declaration(s) to exclude.
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing elements without all specified declaration(s).
 */
fun <T : KoDeclarationProvider> List<T>.withoutAllDeclarations(
    name: String,
    vararg names: String,
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> = filter {
    !it.containsDeclaration(name, includeNested, includeLocal) ||
        if (names.isNotEmpty()) {
            names.any { name -> !it.containsDeclaration(name, includeNested, includeLocal) }
        } else {
            false
        }
}

/**
 * List containing elements without some declarations represented by name.
 *
 * @param name The name of the declaration to exclude.
 * @param names The names of the declarations to exclude.
 * @param includeNested Whether to include nested declarations, by default `false`.
 * @param includeLocal Whether to include local declarations, by default `false`.
 * @return A list containing elements without at least one of the specified declaration(s).
 */
fun <T : KoDeclarationProvider> List<T>.withoutSomeDeclarations(
    name: String,
    vararg names: String,
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> = filter {
    !it.containsDeclaration(name, includeNested, includeLocal) &&
        if (names.isNotEmpty()) {
            names.any { name -> !it.containsDeclaration(name, includeNested, includeLocal) }
        } else {
            true
        }
}
