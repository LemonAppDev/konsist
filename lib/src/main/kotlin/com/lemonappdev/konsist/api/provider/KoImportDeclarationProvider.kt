package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

interface KoImportDeclarationProvider {
    /**
     * The imports of the file.
     */
    val imports: List<KoImportDeclaration>

    /**
     * Whether the file has imports.
     *
     * @param names the names of the imports to check.
     * @return `true` if the file has imports with the specified names (or any import if [names] is empty), `false` otherwise.
     */
    fun hasImports(vararg names: String): Boolean
}
