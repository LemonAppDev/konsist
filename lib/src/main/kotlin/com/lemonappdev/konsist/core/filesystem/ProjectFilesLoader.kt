package com.lemonappdev.konsist.core.filesystem

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File

/**
 * Runs the [scan] once and shares its result with all callers.
 *
 * The scan runs in a dedicated [scope] that no caller owns, so cancelling a caller (e.g. JUnit timeout) only cancels
 * its own wait - the scan continues and its result is reused by later callers. A cancelled or failed scan is not
 * cached - the next call starts a new scan.
 *
 * e.g.
 * 1. getFiles started at Thread 1 - start scanning
 * 2. getFiles started at Thread 2 - scanning in progress, wait for it to complete
 * 3. getFiles started at Thread 1 completes
 * 4. getFiles started at Thread 2 completes
 */
internal class ProjectFilesLoader(
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    private val scan: suspend () -> List<File>,
) {
    private val mutex: Mutex = Mutex()

    @Volatile
    private var filesDeferred: Deferred<List<File>>? = null

    suspend fun getFiles(): List<File> {
        val currentDeferred: Deferred<List<File>> =
            mutex.withLock {
                // isCancelled is also true for a scan that failed with an exception
                filesDeferred
                    ?.takeUnless { it.isCancelled }
                    ?: scope.async { scan() }.also { filesDeferred = it }
            }

        return currentDeferred.await()
    }
}
