package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoImportProvider

/**
 * Sequence containing files with any import.
 *
 * @return A sequence containing files with any import.
 */
fun <T : KoImportProvider> Sequence<T>.withImports(): Sequence<T> = filter { it.hasImports() }

/**
 * Sequence containing files with all specified imports.
 *
 * @param import The import to include.
 * @param imports The import(s) to include.
 * @return A sequence containing files with the specified import(s).
 */
fun <T : KoImportProvider> Sequence<T>.withAllImports(import: String, vararg imports: String): Sequence<T> = filter {
    it.hasImports(import, *imports)
}

/**
 * Sequence containing files with some imports.
 *
 * @param import The import to include.
 * @param imports The imports to include.
 * @return A sequence containing files with at least one of the specified import(s).
 */
fun <T : KoImportProvider> Sequence<T>.withSomeImports(import: String, vararg imports: String): Sequence<T> = filter {
    it.hasImports(import) || imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing files with no import.
 *
 * @return A sequence containing files with no import.
 */
fun <T : KoImportProvider> Sequence<T>.withoutImports(): Sequence<T> = filterNot { it.hasImports() }

/**
 * Sequence containing files without all specified imports.
 *
 * @param import The import to exclude.
 * @param imports The import(s) to exclude.
 * @return A sequence containing files without specified import(s).
 */
fun <T : KoImportProvider> Sequence<T>.withoutAllImports(import: String, vararg imports: String): Sequence<T> = filterNot {
    it.hasImports(import, *imports)
}

/**
 * Sequence containing files without some imports.
 *
 * @param import The import to exclude.
 * @param imports The imports to exclude.
 * @return A sequence containing files without at least one of the specified import(s).
 */
fun <T : KoImportProvider> Sequence<T>.withoutSomeImports(import: String, vararg imports: String): Sequence<T> = filter {
    !it.hasImports(import) && if (imports.isNotEmpty()) {
        imports.any { import -> !it.hasImports(import) }
    } else {
        true
    }
}
