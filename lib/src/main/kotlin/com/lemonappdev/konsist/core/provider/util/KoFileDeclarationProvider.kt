package com.lemonappdev.konsist.core.provider.util

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.filesystem.PathProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File

internal object KoFileDeclarationProvider {
    private val projectRootDir: File = File(PathProvider.rootProjectPath)

    private val mutex: Mutex = Mutex()

    @Volatile
    private var createKoFilesDeclarationDeferred: Deferred<List<KoFileDeclaration>>? = null

    init {
        check(projectRootDir.exists()) { "Directory does not exist: ${projectRootDir.absolutePath}" }
        check(projectRootDir.isDirectory) { "Project root directory is a File ${projectRootDir.absolutePath}" }

        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                getKoFileDeclarations()
            }
        }
    }

    /**
     * Retrieves a list of [KoFileDeclaration]s asynchronously from the project's root directory.
     * This function scans the directory for Kotlin files and parses them to obtain list of KoFileDeclaration.
     *
     * The parsing operations are performed concurrently.
     *
     * Threading Strategy:
     * Ensures thread-safe initialization of a single deferred operation to parse all Kotlin files, using a mutex to
     * guard against concurrent initializations.
     * File walking and parsing operations, optimizing for I/O operations are performed concurrently across
     * multiple threads.
     *
     * e.g.
     * 1. getKoFileDeclarations started at Thread 1 - start file parsing
     * 2. getKoFileDeclarations started at Thread 2 - file parsing in progress, wait for it to complete
     * 3. Parse Kotlin files in parallel
     * 4. getKoFileDeclarations  started at Thread 1 completes
     * 5. getKoFileDeclarations  started at Thread 2 completes
     *
     * @return A list of [KoFileDeclaration]s representing the parsed Kotlin files.
     * @throws Exception if there's an issue accessing the file system or parsing the files.
     */
    suspend fun getKoFileDeclarations(): List<KoFileDeclaration> =
        coroutineScope {
            val currentDeferred: Deferred<List<KoFileDeclaration>> =
                mutex.withLock {
                    createKoFilesDeclarationDeferred ?: async(Dispatchers.IO) {
                        projectRootDir
                            .walk()
                            .filter { it.isKotlinFile }
                            .map { async { parseKotlinFile(it) } }
                            .toList()
                            .awaitAll()
                    }.also { createKoFilesDeclarationDeferred = it }
                }

            currentDeferred.await()
        }

    private suspend fun parseKotlinFile(it: File): KoFileDeclaration =
        coroutineScope {
            async { it.toKoFile() }.await()
        }
}
