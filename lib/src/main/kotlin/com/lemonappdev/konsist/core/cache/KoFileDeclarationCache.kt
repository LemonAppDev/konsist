package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Caches parsed files by path, so every scope (e.g. project scope and directory scope) uses the same file declaration.
 * Parsing the same file again would keep another copy of its PSI tree and declarations in memory (declarations are
 * cached for the lifetime of the process).
 */
internal object KoFileDeclarationCache {
    private val elements = ConcurrentHashMap<String, KoFileDeclaration>()

    fun getOrCreateInstance(
        file: File,
        value: (File) -> KoFileDeclaration,
    ): KoFileDeclaration = elements[file.path] ?: value(file).let { elements.putIfAbsent(file.path, it) ?: it }
}
