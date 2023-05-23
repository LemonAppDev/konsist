package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromProjectFileTest {
    @Test
    fun `scopeFromProjectFile`() {
        // given
        val sut = Konsist
            .scopeFromProjectFile("/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt"),
        )
    }

    @Test
    fun `scopeFromProjectFile throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromProjectFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt") }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory/NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProjectFile throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromProjectFile("app/src/main/kotlin/com/lemonappdev/sample") }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory/sample"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
