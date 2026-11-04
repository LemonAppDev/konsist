package com.lemonappdev.konsist.core.filesystem

import com.lemonappdev.konsist.core.ext.isKotlinFile
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File

/**
 * Scans the project directory for Kotlin files. Files are not parsed.
 *
 * Every directory is scanned in a separate coroutine, so independent subtrees are listed in parallel (directory
 * listing is blocking I/O, so [Dispatchers.IO] is used). Directories ignored by [ProjectDirectoryFilter] (build
 * outputs, hidden directories, node_modules) are not scanned.
 *
 * Files are returned in the same order as a sequential pre-order walk ([File.walk]).
 */
internal class ProjectKotlinFileScanner(
    private val projectRootDir: File,
) {
    private val projectDirectoryFilter = ProjectDirectoryFilter(projectRootDir)

    suspend fun scan(): List<File> = scanDirectory(projectRootDir)

    private suspend fun scanDirectory(directory: File): List<File> =
        coroutineScope {
            directory
                .listFiles()
                .orEmpty()
                .map { file ->
                    // Kotlin file check goes first, so each Kotlin file is checked on the file system once
                    when {
                        file.isKotlinFile -> {
                            CompletableDeferred(listOf(file))
                        }

                        file.isDirectory && !projectDirectoryFilter.isIgnored(file) -> {
                            async(Dispatchers.IO) { scanDirectory(file) }
                        }

                        else -> {
                            CompletableDeferred(emptyList())
                        }
                    }
                }.awaitAll()
                .flatten()
        }
}
