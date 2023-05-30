package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.sep
import com.lemonappdev.konsist.helper.ext.toNormalizedPath
import com.lemonappdev.konsist.helper.ext.toNormalizedPaths
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
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/".toNormalizedPath(), absolutePath = true)
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
            .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/sample/".toNormalizedPath(), absolutePath = false)
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
        val func = { Konsist.scopeFromDirectory("$appMainSourceSetDirectory/nonExisting/".toNormalizedPath(), absolutePath = true) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${sep}nonExisting${sep}"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath false throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/".toNormalizedPath(), absolutePath = false) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${sep}nonExisting${sep}"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath true throws exception if path points to file`() {
        // given
        val func = { Konsist.scopeFromDirectory("$appMainSourceSetDirectory/sample/AppClass.kt".toNormalizedPath(), absolutePath = true) }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory${sep}sample${sep}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath false throws exception if path points to file`() {
        // given
        val func =
            {
                Konsist.scopeFromDirectory(
                    "app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toNormalizedPath(),
                    absolutePath = false
                )
            }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory${sep}sample${sep}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
