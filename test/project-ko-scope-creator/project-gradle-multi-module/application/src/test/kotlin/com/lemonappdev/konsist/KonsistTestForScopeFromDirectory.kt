package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.util.PathProvider.applicationMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

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

    private fun KoScope.mapToFilePaths() = files()
        .toList()
        .map { it.filePath }
}
