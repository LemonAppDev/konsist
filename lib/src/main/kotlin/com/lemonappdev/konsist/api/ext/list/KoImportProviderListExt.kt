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
 * List containing declarations with all specified imports.
 *
 * @param name The import name to include.
 * @param names The import name(s) to include.
 * @return A list containing declarations with the specified import(s).
 */
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
fun <T : KoImportProvider> List<T>.withSomeImports(name: String, vararg names: String): List<T> = filter {
    it.hasImports(name) || names.any { import -> it.hasImports(import) }
}

/**
 * List containing declarations with no import.
 *
 * @return A list containing declarations with no import.
 */
fun <T : KoImportProvider> List<T>.withoutImports(): List<T> = filterNot { it.hasImports() }

/**
 * List containing declarations without all specified imports.
 *
 * @param name The import name to exclude.
 * @param names The import name(s) to exclude.
 * @return A list containing declarations without specified import(s).
 */
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
fun <T : KoImportProvider> List<T>.withoutSomeImports(name: String, vararg names: String): List<T> = filter {
    !it.hasImports(name) && if (names.isNotEmpty()) {
        names.any { import -> !it.hasImports(import) }
    } else {
        true
    }
}
