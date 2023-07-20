package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

interface KoImportProvider : KoBaseProvider {
    /**
     * Sequence of imports.
     */
    val imports: Sequence<KoImportDeclaration>

    /**
     * Whether the file or scope has imports.
     *
     * @param names the names of the imports to check.
     * @return `true` if the file or scope has imports with the specified names (or any import if [names] is empty), `false` otherwise.
     */
    fun hasImports(vararg names: String): Boolean
}
