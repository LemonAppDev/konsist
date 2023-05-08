package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTestForScopeFromDirectory {
    @Test
    fun `scopeFromDirectory`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$applicationMainSourceSetDirectory/sample/")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromDirectory throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromDirectory("$applicationMainSourceSetDirectory/nonExisting/") }

        // then
        val message = "Directory does not exist: $applicationMainSourceSetDirectory/nonExisting/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory throws exception if path points to file`() {
        // given
        val func = { Konsist.scopeFromDirectory("$applicationMainSourceSetDirectory/sample/AppClass.kt") }

        // then
        val message = "Path is a file, but should be a directory: $applicationMainSourceSetDirectory/sample/AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    companion object {
        private val projectRootDirectory = File("")
            .absoluteFile
            .path
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)

        private val applicationMainSourceSetDirectory = "$projectRootDirectory/application/src/main/kotlin/com/lemonappdev"

        private val applicationTestSourceSetDirectory = "$projectRootDirectory/application/src/test/kotlin/com/lemonappdev"

        private val libraryMainSourceSetDirectory = "$projectRootDirectory/library/src/main/kotlin/com/lemonappdev"

        private val libraryTestSourceSetDirectory = "$projectRootDirectory/library/src/test/kotlin/com/lemonappdev"
    }

    private fun KoScope.mapToFilePaths() = files()
        .toList()
        .map { it.filePath }
}
