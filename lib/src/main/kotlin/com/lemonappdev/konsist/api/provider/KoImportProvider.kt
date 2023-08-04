package com.lemonappdev.konsist.api.provider

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
     * Whether the declaration has imports.
     *
     * @param names the names of the imports to check.
     * @return `true` if the declaration has imports with the specified names (or any import if [names] is empty), `false` otherwise.
     */
    fun hasImports(vararg names: String): Boolean
}
