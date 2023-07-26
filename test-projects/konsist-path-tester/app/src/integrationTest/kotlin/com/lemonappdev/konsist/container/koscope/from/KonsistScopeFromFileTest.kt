package com.lemonappdev.konsist.container.koscope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromFileTest {

    @Test
    fun `scopeFromFile`() {
        // given
        val sut = Konsist
            .scopeFromFile("/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toOsSeparator())
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$appMainSourceSetDirectory/sample/AppClass.kt").toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt".toOsSeparator()) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${fileSeparator}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile throws exception if path points to directory`() {
        // given
        val func = { Konsist.scopeFromFile("app/src/main/kotlin/com/lemonappdev/sample".toOsSeparator()) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory${fileSeparator}sample"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
