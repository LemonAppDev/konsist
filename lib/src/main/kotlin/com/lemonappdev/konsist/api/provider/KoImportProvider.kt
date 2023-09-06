package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to import declarations.
 */
interface KoImportProvider : KoBaseProvider {
    /**
     * List of imports.
     */
    val imports: List<KoImportDeclaration>

    /**
     * The number of imports.
     */
    val numImports: Int

    /**
     * Gets the number of imports that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an import satisfies a condition.
     * @return The number of imports in the declaration.
     */
    fun countImports(predicate: (KoImportDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has imports.
     *
     * @param names the names of the imports to check.
     * @return `true` if the declaration has imports with the specified names (or any import if [names] is empty), `false` otherwise.
     */
    fun hasImports(vararg names: String): Boolean
}
