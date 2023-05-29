package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.ext.toNormalizedPaths
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromDirectoryTest {
    @Test
    fun `scopeFromDirectory with absolutePath true`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/", absolutePath = true)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toNormalizedPaths(),
        )
    }

    @Test
    fun `scopeFromDirectory with absolutePath false`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/sample/", absolutePath = false)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toNormalizedPaths(),
        )
    }

    @Test
    fun `scopeFromDirectory with absolutePath true throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromDirectory("$appMainSourceSetDirectory/nonExisting/", absolutePath = true) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory/nonExisting/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath false throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/", absolutePath = false) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory/nonExisting/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath true throws exception if path points to file`() {
        // given
        val func = { Konsist.scopeFromDirectory("$appMainSourceSetDirectory/sample/AppClass.kt", absolutePath = true) }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory/sample/AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath false throws exception if path points to file`() {
        // given
        val func =
            { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt", absolutePath = false) }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory/sample/AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
