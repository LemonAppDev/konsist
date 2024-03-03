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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal object KoFileDeclarationProvider {
    private val projectRootDir: File = File(PathProvider.rootProjectPath)

    private val mutex: Mutex = Mutex()

    @Volatile
    private var createKoFilesDeclarationDeferred: Deferred<List<KoFileDeclaration>>? = null

    init {
        // TODO: Remove this print
        println("AAAA ${getCurrentTimeFormatted()} KoDeclarationProvider created at ${Thread.currentThread()}")

        check(projectRootDir.isDirectory) { "Project root directory is a File" }

        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                getKoFileDeclarations()
            }
        }
    }

    suspend fun getKoFileDeclarations(): List<KoFileDeclaration> = coroutineScope {
        // TODO: Remove this print
        println("AAAA ${getCurrentTimeFormatted()} getKoFileDeclarations start at ${Thread.currentThread()}")

        val currentDeferred: Deferred<List<KoFileDeclaration>>

        mutex.withLock {
            if (createKoFilesDeclarationDeferred == null) {
                createKoFilesDeclarationDeferred = async(Dispatchers.IO) {
                    projectRootDir.walk()
                        .filter { it.isKotlinFile }
                        .map {
                            async { parseKotlinFile(it) }
                        }
                        .toList() // Convert the sequence to a list to start all coroutines
                        .awaitAll() // Wait for all parsing operations to complete
                }
            }

            currentDeferred = createKoFilesDeclarationDeferred!!
        }

        val await = currentDeferred.await()
        // TODO: Remove this print
        println("AAAA ${getCurrentTimeFormatted()} getKoFileDeclarations end at ${Thread.currentThread()}")
        return@coroutineScope await
    }

    private suspend fun parseKotlinFile(it: File): KoFileDeclaration = coroutineScope {
        async {
            // TODO: Remove this print
            println("AAAA ${getCurrentTimeFormatted()} Parsing file at ${Thread.currentThread()}")
            it.toKoFile()
        }.await()
    }
}

// TODO: Remove
fun getCurrentTimeFormatted(): String {
    val now = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS")
    return now.format(formatter)
}
