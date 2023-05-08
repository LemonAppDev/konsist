package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.util.PathProvider.applicationMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

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

    private fun KoScope.mapToFilePaths() = files()
        .toList()
        .map { it.filePath }
}
