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
 * List containing declarations whose imports all satisfy the predicate.
 *
 * @param predicate The predicate function to determine if a declaration import satisfies a condition.
 * @return A list containing declarations with the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withAllImports(predicate: (KoImportDeclaration) -> Boolean): List<T> = filter {
    it.hasAllImports(predicate)
}

/**
 * List containing declarations that have import that satisfies a condition.
 *
 * @param predicate The predicate function to determine if a declaration import satisfies a condition.
 * @return A list containing declarations with the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withImport(predicate: (KoImportDeclaration) -> Boolean): List<T> = filter {
    it.hasImport(predicate)
}

/**
 * List containing declarations with all specified imports.
 *
 * @param name The import name to include.
 * @param names The import name(s) to include.
 * @return A list containing declarations with the specified import(s).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withAllImports { it.name == ... }"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withImport { it.name == ... }"))
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
 * List containing declarations for which all imports not satisfy the condition.
 *
 * @param predicate The predicate function to determine if a declaration import satisfies a condition.
 * @return A list containing declarations without the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutAllImports(predicate: (KoImportDeclaration) -> Boolean): List<T> = filterNot {
    it.hasAllImports(predicate)
}

/**
 * List containing declarations that have at least one import which not satisfies a condition.
 *
 * @param predicate The predicate function to determine if a declaration import satisfies a condition.
 * @return A list containing declarations with the specified import(s).
 */
fun <T : KoImportProvider> List<T>.withoutImport(predicate: (KoImportDeclaration) -> Boolean): List<T> = filterNot {
    it.hasImport(predicate)
}

/**
 * List containing declarations without all specified imports.
 *
 * @param name The import name to exclude.
 * @param names The import name(s) to exclude.
 * @return A list containing declarations without specified import(s).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutAllImports { it.name == ... }"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutImport { it.name == ... }"))
fun <T : KoImportProvider> List<T>.withoutSomeImports(name: String, vararg names: String): List<T> = filter {
    !it.hasImports(name) && if (names.isNotEmpty()) {
        names.any { import -> !it.hasImports(import) }
    } else {
        true
    }
}
