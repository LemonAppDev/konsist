package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoImportProvider

/**
 * List containing files with any import.
 *
 * @return A list containing files with any import.
 */
fun <T : KoImportProvider> List<T>.withImports(): List<T> = filter { it.hasImports() }

/**
 * List containing files with all specified imports.
 *
 * @param import The import to include.
 * @param imports The import(s) to include.
 * @return A list containing files with the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withAllImports(import: String, vararg imports: String): List<T> = filter {
    it.hasImports(import, *imports)
}

/**
 * List containing files with some imports.
 *
 * @param import The import to include.
 * @param imports The imports to include.
 * @return A list containing files with at least one of the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withSomeImports(import: String, vararg imports: String): List<T> = filter {
    it.hasImports(import) || imports.any { import -> it.hasImports(import) }
}

/**
 * List containing files with no import.
 *
 * @return A list containing files with no import.
 */
fun <T : KoImportProvider> List<T>.withoutImports(): List<T> = filterNot { it.hasImports() }

/**
 * List containing files without all specified imports.
 *
 * @param import The import to exclude.
 * @param imports The import(s) to exclude.
 * @return A list containing files without specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutAllImports(import: String, vararg imports: String): List<T> = filterNot {
    it.hasImports(import, *imports)
}

/**
 * List containing files without some imports.
 *
 * @param import The import to exclude.
 * @param imports The imports to exclude.
 * @return A list containing files without at least one of the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutSomeImports(import: String, vararg imports: String): List<T> = filter {
    !it.hasImports(import) && if (imports.isNotEmpty()) {
        imports.any { import -> !it.hasImports(import) }
    } else {
        true
    }
}
