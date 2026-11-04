package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import java.lang.ref.SoftReference

/**
 * Caches parsed files by path, so every scope (e.g. project scope and directory scope) uses the same file declaration.
 * Parsing the same file again would keep another copy of its PSI tree and declarations in memory.
 *
 * Files are cached using soft references, so instead of throwing `OutOfMemoryError` the garbage collector releases
 * files (with their declarations stored in the PSI tree) that are not used, and they are parsed again when needed.
 * File is not released while any of its declarations is used (declaration references its PSI element, which references
 * the file), so the same declaration instances are returned for the file as long as any of them is used.
 */
internal object KoFileDeclarationCache {
    private val elements = KoReferenceCache<KtFile> { SoftReference(it) }

    fun getOrCreateInstance(
        file: File,
        value: (File) -> KtFile,
    ): KoFileDeclaration = KoFileDeclarationCore.getInstance(elements.getOrCreateInstance(file.path) { value(file) })

    /**
     * Returns the file declaration or `null` if the file is not parsed yet (or it was released from memory).
     */
    fun getInstanceOrNull(file: File): KoFileDeclaration? = elements.get(file.path)?.let { KoFileDeclarationCore.getInstance(it) }
}
