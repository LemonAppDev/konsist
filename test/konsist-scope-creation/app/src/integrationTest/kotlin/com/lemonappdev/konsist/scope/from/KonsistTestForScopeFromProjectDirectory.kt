package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistTestForScopeFromProjectDirectory {
    @Test
    fun `scopeFromProjectDirectory`() {
        // given
        val sut = Konsist
            .scopeFromProjectDirectory("app/src/main/kotlin/com/lemonappdev/sample/")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProjectDirectory throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromProjectDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/") }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory/nonExisting/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromProjectDirectory throws exception if path points to file`() {
        // given
        val func = { Konsist.scopeFromProjectDirectory("app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt") }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory/sample/AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
