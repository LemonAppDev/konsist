package com.lemonappdev.konsist.core.filesystem

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

class ProjectFilesLoaderTest {
    private val files = listOf(File("A.kt"), File("B.kt"))

    private val scanCount = AtomicInteger(0)

    @Test
    fun `getFiles scans once when called multiple times`() {
        // given
        val sut = ProjectFilesLoader { files.also { scanCount.incrementAndGet() } }

        // when
        val actual = runBlocking { List(3) { sut.getFiles() } }

        // then
        actual shouldBeEqualTo List(3) { files }
        scanCount.get() shouldBeEqualTo 1
    }

    @Test
    fun `getFiles shares single scan between concurrent callers`() {
        // given
        val scanGate = CompletableDeferred<Unit>()
        val sut =
            ProjectFilesLoader {
                scanCount.incrementAndGet()
                scanGate.await()
                files
            }

        // when
        val actual =
            runBlocking {
                val callers = List(10) { async(Dispatchers.Default) { sut.getFiles() } }
                scanGate.complete(Unit)
                callers.awaitAll()
            }

        // then
        actual shouldBeEqualTo List(10) { files }
        scanCount.get() shouldBeEqualTo 1
    }

    @Test
    fun `getFiles returns files when first caller was cancelled during scan`() {
        // given
        val scanStarted = CompletableDeferred<Unit>()
        val scanGate = CompletableDeferred<Unit>()
        val sut =
            ProjectFilesLoader {
                scanCount.incrementAndGet()
                scanStarted.complete(Unit)
                scanGate.await()
                files
            }

        // when
        val actual =
            runBlocking {
                val firstCaller = launch(Dispatchers.Default) { sut.getFiles() }
                scanStarted.await()
                firstCaller.cancelAndJoin()

                val secondCaller = async(Dispatchers.Default) { sut.getFiles() }
                scanGate.complete(Unit)
                secondCaller.await()
            }

        // then
        actual shouldBeEqualTo files
        // Scan is not owned by the cancelled caller, so it completes and is reused
        scanCount.get() shouldBeEqualTo 1
    }

    @Test
    fun `getFiles retries scan when previous scan failed`() {
        // given
        val sut =
            ProjectFilesLoader {
                when (scanCount.incrementAndGet()) {
                    1 -> error("Scan failed")
                    else -> files
                }
            }

        // when
        val func = { runBlocking { sut.getFiles() } }

        // then
        func shouldThrow IllegalStateException::class
        func() shouldBeEqualTo files
        scanCount.get() shouldBeEqualTo 2
    }

    @Test
    fun `getFiles retries scan when previous scan was cancelled`() {
        // given
        val sut =
            ProjectFilesLoader {
                when (scanCount.incrementAndGet()) {
                    1 -> throw CancellationException("Scan cancelled")
                    else -> files
                }
            }

        // when
        val firstResult = runCatching { runBlocking { sut.getFiles() } }
        val actual = runBlocking { sut.getFiles() }

        // then
        firstResult.isFailure shouldBeEqualTo true
        actual shouldBeEqualTo files
        scanCount.get() shouldBeEqualTo 2
    }
}
