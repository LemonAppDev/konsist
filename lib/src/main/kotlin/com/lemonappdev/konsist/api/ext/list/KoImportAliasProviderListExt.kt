package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider

/**
 * List containing import alias declarations.
 */
val <T : KoImportAliasProvider> List<T>.importAliases: List<KoImportAliasDeclaration>
    get() = flatMap { it.importAliases }

/**
 * List containing declarations with any import alias.
 *
 * @return A list containing declarations with any import alias.
 */
fun <T : KoImportAliasProvider> List<T>.withImportAliases(): List<T> = filter { it.hasImportAliases() }

/**
 * List containing declarations with no import alias.
 *
 * @return A list containing declarations with no import alias.
 */

fun <T : KoImportAliasProvider> List<T>.withoutImportAliases(): List<T> = filterNot { it.hasImportAliases() }

/**
 * List containing declarations that have at least one import alias with the specified name(s).
 *
 * @param name The name of the import alias to include.
 * @param names The names of additional import aliass to include.
 * @return A list containing declarations with at least one of the specified import alias(es).
 */
fun <T : KoImportAliasProvider> List<T>.withImportAliasNamed(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasImportAliasWithName(name, *names)
    }

/**
 * List containing declarations without any of specified import aliass.
 *
 * @param name The name of the import alias to exclude.
 * @param names The names of additional import aliass to exclude.
 * @return A list containing declarations without any of specified import aliass.
 */
fun <T : KoImportAliasProvider> List<T>.withoutImportAliasNamed(
    name: String,
    vararg names: String,
): List<T> = filterNot { it.hasImportAliasWithName(name, *names) }

/**
 * List containing declarations that have all specified import aliass.
 *
 * @param name The name of the import alias to include.
 * @param names The name(s) of the import alias(es) to include.
 * @return A list containing declarations with all specified import alias(es).
 */
fun <T : KoImportAliasProvider> List<T>.withAllImportAliasesNamed(
    name: String,
    vararg names: String,
): List<T> = filter { it.hasImportAliasesWithAllNames(name, *names) }

/**
 * List containing declarations without all specified import aliass.
 *
 * @param name The name of the import alias to exclude.
 * @param names The name(s) of the import alias(es) to exclude.
 * @return A list containing declarations without all specified import alias(es).
 */
fun <T : KoImportAliasProvider> List<T>.withoutAllImportAliasesNamed(
    name: String,
    vararg names: String,
): List<T> = filterNot { it.hasImportAliasesWithAllNames(name, *names) }

/**
 * List containing declarations that have at least one import alias satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an import alias declaration.
 * @return A list containing declarations with at least one import alias satisfying the predicate.
 */
fun <T : KoImportAliasProvider> List<T>.withImportAlias(predicate: (KoImportAliasDeclaration) -> Boolean): List<T> =
    filter {
        it.hasImportAlias(predicate)
    }

/**
 * List containing declarations that not have import alias satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an import alias declaration.
 * @return A list containing declarations without import alias satisfying the provided predicate.
 */
fun <T : KoImportAliasProvider> List<T>.withoutImportAlias(predicate: (KoImportAliasDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasImportAlias(predicate)
    }

/**
 * List containing declarations that have all import aliass satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all import alias declarations.
 * @return A filtered list containing declarations with all import aliass satisfying the predicate.
 */
fun <T : KoImportAliasProvider> List<T>.withAllImportAliases(predicate: (KoImportAliasDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllImportAliases(predicate)
    }

/**
 * List containing declarations that have at least one import alias not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all import alias declarations.
 * @return A list containing declarations that have at least one import alias not satisfying the provided predicate.
 */
fun <T : KoImportAliasProvider> List<T>.withoutAllImportAliases(predicate: (KoImportAliasDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasAllImportAliases(predicate)
    }

/**
 * List containing declarations with import alias declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of import alias declarations.
 * @return A list containing declarations with import alias declarations satisfying the predicate.
 */
fun <T : KoImportAliasProvider> List<T>.withImportAliases(predicate: (List<KoImportAliasDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.importAliases) }

/**
 * List containing declarations without import alias declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of import alias declarations.
 * @return A list containing declarations without import alias declarations satisfying the predicate.
 */
fun <T : KoImportAliasProvider> List<T>.withoutImportAliases(predicate: (List<KoImportAliasDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.importAliases) }
