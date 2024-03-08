package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider

/**
 * List containing import declarations.
 */
val <T : KoImportProvider> List<T>.imports: List<KoImportDeclaration>
    get() = flatMap { it.imports }

/**
 * List containing declarations with any import.
 *
 * @return A list containing declarations with any import.
 */
fun <T : KoImportProvider> List<T>.withImports(): List<T> = filter { it.hasImports() }

/**
 * List containing declarations with no import.
 *
 * @return A list containing declarations with no import.
 */

fun <T : KoImportProvider> List<T>.withoutImports(): List<T> = filterNot { it.hasImports() }

/**
 * List containing declarations that have at least one import with the specified name(s).
 *
 * @param name The name of the import to include.
 * @param names The names of additional imports to include.
 * @return A list containing declarations with at least one of the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withImportNamed(name: String, vararg names: String): List<T> = filter {
    it.hasImportWithName(name, *names)
}

/**
 * List containing declarations without any of specified imports.
 *
 * @param name The name of the import to exclude.
 * @param names The names of additional imports to exclude.
 * @return A list containing declarations without any of specified imports.
 */
fun <T : KoImportProvider> List<T>.withoutImportNamed(name: String, vararg names: String): List<T> =
    filterNot { it.hasImportWithName(name, *names) }

/**
 * List containing declarations that have all specified imports.
 *
 * @param name The name of the import to include.
 * @param names The name(s) of the import(s) to include.
 * @return A list containing declarations with all specified import(s).
 */
fun <T : KoImportProvider> List<T>.withAllImportsNamed(name: String, vararg names: String): List<T> =
    filter { it.hasImportsWithAllNames(name, *names) }

/**
 * List containing declarations without all specified imports.
 *
 * @param name The name of the import to exclude.
 * @param names The name(s) of the import(s) to exclude.
 * @return A list containing declarations without all specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutAllImportsNamed(name: String, vararg names: String): List<T> =
    filterNot { it.hasImportsWithAllNames(name, *names) }

/**
 * List containing declarations that have at least one import satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an import declaration.
 * @return A list containing declarations with at least one import satisfying the predicate.
 */
fun <T : KoImportProvider> List<T>.withImport(predicate: (KoImportDeclaration) -> Boolean): List<T> = filter {
    it.hasImport(predicate)
}

/**
 * List containing declarations that not have import satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an import declaration.
 * @return A list containing declarations without import satisfying the provided predicate.
 */
fun <T : KoImportProvider> List<T>.withoutImport(predicate: (KoImportDeclaration) -> Boolean): List<T> = filterNot {
    it.hasImport(predicate)
}

/**
 * List containing declarations that have all imports satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all import declarations.
 * @return A filtered list containing declarations with all imports satisfying the predicate.
 */
fun <T : KoImportProvider> List<T>.withAllImports(predicate: (KoImportDeclaration) -> Boolean): List<T> = filter {
    it.hasAllImports(predicate)
}

/**
 * List containing declarations that have at least one import not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all import declarations.
 * @return A list containing declarations that have at least one import not satisfying the provided predicate.
 */
fun <T : KoImportProvider> List<T>.withoutAllImports(predicate: (KoImportDeclaration) -> Boolean): List<T> = filterNot {
    it.hasAllImports(predicate)
}

/**
 * List containing declarations with import declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of import declarations.
 * @return A list containing declarations with import declarations satisfying the predicate.
 */
fun <T : KoImportProvider> List<T>.withImports(predicate: (List<KoImportDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.imports) }

/**
 * List containing declarations without import declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of import declarations.
 * @return A list containing declarations without import declarations satisfying the predicate.
 */
fun <T : KoImportProvider> List<T>.withoutImports(predicate: (List<KoImportDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.imports) }

/**
 * List containing declarations with all specified imports.
 *
 * @param name The import name to include.
 * @param names The import name(s) to include.
 * @return A list containing declarations with the specified import(s).
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withImportNamed`, otherwise with `withAllImportsNamed`.
            """,
    ReplaceWith("withImportNamed/withAllImportsNamed"),
)
fun <T : KoImportProvider> List<T>.withAllImports(name: String, vararg names: String): List<T> = filter {
    it.hasImports(name, *names)
}

/**
 * List containing declarations with some imports.
 *
 * @param name The import name to include.
 * @param names The import name(s) to include.
 * @return A list containing declarations with at least one of the specified import(s).
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withImportNamed(*names"))
fun <T : KoImportProvider> List<T>.withSomeImports(name: String, vararg names: String): List<T> = filter {
    it.hasImports(name) || names.any { import -> it.hasImports(import) }
}

/**
 * List containing declarations without all specified imports.
 *
 * @param name The import name to exclude.
 * @param names The import name(s) to exclude.
 * @return A list containing declarations without specified import(s).
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withoutImportNamed`, otherwise with `withoutAllImportsNamed`.
            """,
    ReplaceWith("withoutImportNamed/withoutAllImportsNamed"),
)
fun <T : KoImportProvider> List<T>.withoutAllImports(name: String, vararg names: String): List<T> = filterNot {
    it.hasImports(name, *names)
}

/**
 * List containing declarations without some imports.
 *
 * @param name The import name to exclude.
 * @param names The import name(s) to exclude.
 * @return A list containing declarations without at least one of the specified import(s).
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withoutImportNamed(*names"))
fun <T : KoImportProvider> List<T>.withoutSomeImports(name: String, vararg names: String): List<T> = filter {
    val missesAtLeastOneImport = if (names.isNotEmpty()) {
        names.any { import -> !it.hasImports(import) }
    } else {
        true
    }

    !it.hasImports(name) && missesAtLeastOneImport
}
