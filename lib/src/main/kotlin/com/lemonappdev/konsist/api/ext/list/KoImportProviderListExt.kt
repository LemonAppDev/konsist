package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider

/**
 * List containing import declarations.
 */
val <T : KoImportProvider> List<T>.imports: List<KoImportDeclaration>
    get() = flatMap { it.imports }

/**
 * List containing elements with any import.
 *
 * @return A list containing elements with any import.
 */
fun <T : KoImportProvider> List<T>.withImports(): List<T> = filter { it.hasImports() }

/**
 * List containing elements with all specified imports.
 *
 * @param name The import name to include.
 * @param names The import name(s) to include.
 * @return A list containing elements with the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withAllImports(name: String, vararg names: String): List<T> = filter {
    it.hasImports(name, *names)
}

/**
 * List containing elements with some imports.
 *
 * @param name The import name to include.
 * @param names The import name(s) to include.
 * @return A list containing elements with at least one of the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withSomeImports(name: String, vararg names: String): List<T> = filter {
    it.hasImports(name) || names.any { import -> it.hasImports(import) }
}

/**
 * List containing elements with no import.
 *
 * @return A list containing elements with no import.
 */
fun <T : KoImportProvider> List<T>.withoutImports(): List<T> = filterNot { it.hasImports() }

/**
 * List containing elements without all specified imports.
 *
 * @param name The import name to exclude.
 * @param names The import name(s) to exclude.
 * @return A list containing elements without specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutAllImports(name: String, vararg names: String): List<T> = filterNot {
    it.hasImports(name, *names)
}

/**
 * List containing elements without some imports.
 *
 * @param name The import name to exclude.
 * @param names The import name(s) to exclude.
 * @return A list containing elements without at least one of the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutSomeImports(name: String, vararg names: String): List<T> = filter {
    !it.hasImports(name) && if (names.isNotEmpty()) {
        names.any { import -> !it.hasImports(import) }
    } else {
        true
    }
}
