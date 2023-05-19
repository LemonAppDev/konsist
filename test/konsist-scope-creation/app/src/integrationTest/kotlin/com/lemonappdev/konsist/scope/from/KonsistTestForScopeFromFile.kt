package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistTestForScopeFromFile {
    @Test
    fun `scopeFromFile`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetDirectory/sample/AppClass.kt")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt"),
        )
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("$appMainSourceSetDirectory/NonExisting.kt") }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory/NonExisting.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("$appMainSourceSetDirectory/sample/") }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory/sample/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
