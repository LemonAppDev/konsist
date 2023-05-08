package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTestForScopeFromFile {
    @Test
    fun `scopeFromFile`() {
        // given
        val sut = Konsist
            .scopeFromFile("$applicationMainSourceSetDirectory/sample/AppClass.kt")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$applicationMainSourceSetDirectory/sample/AppClass.kt"),
        )
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("$applicationMainSourceSetDirectory/NonExisting.kt") }

        // then
        val message = "File does not exist: $applicationMainSourceSetDirectory/NonExisting.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("$applicationMainSourceSetDirectory/sample/") }

        // then
        val message = "Path is a directory, but should be a file: $applicationMainSourceSetDirectory/sample/"
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
