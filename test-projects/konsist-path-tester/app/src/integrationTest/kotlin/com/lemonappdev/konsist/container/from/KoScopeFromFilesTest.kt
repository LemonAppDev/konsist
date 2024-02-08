package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromFilesTest {

    @Test
    fun `scopeFromFiles`() {
        // given
        val files = setOf(
            "/app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toOsSeparator(),
            "/app/src/main/kotlin/com/lemonappdev/sample/data/AppDataClass.kt".toOsSeparator()
        )
        val sut = Konsist.scopeFromFiles(files)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromFiles throws exception if path does not exist`() {
        // given
        val files = setOf(
            "app/src/main/kotlin/com/lemonappdev/NonExistingTest.kt".toOsSeparator()
        )

        val func =
            { Konsist.scopeFromFiles(files) }

        // then
        val message = "File does not exist: $appMainSourceSetDirectory${fileSeparator}NonExistingTest.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFiles throws exception if path points to directory`() {
        // given
        val files = setOf(
            "app/src/main/kotlin/com/lemonappdev/sample".toOsSeparator()
        )

        val func = { Konsist.scopeFromFiles(files) }

        // then
        val message = "Path is a directory, but should be a file: $appMainSourceSetDirectory${fileSeparator}sample"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
