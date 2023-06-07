package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.sep
import com.lemonappdev.konsist.helper.ext.toCanonicalPaths
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
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/".toCanonicalPaths(), absolutePath = true)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toCanonicalPaths(),
        )
    }

    @Test
    fun `scopeFromDirectory with absolutePath false`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/sample/".toCanonicalPaths(), absolutePath = false)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toCanonicalPaths(),
        )
    }

    @Test
    fun `scopeFromDirectory with absolutePath true throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromDirectory("$appMainSourceSetDirectory/nonExisting/".toCanonicalPaths(), absolutePath = true) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${sep}nonExisting$sep"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath false throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/".toCanonicalPaths(), absolutePath = false) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${sep}nonExisting$sep"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory with absolutePath true throws exception if path points to file`() {
        // given
        val func = { Konsist.scopeFromDirectory("$appMainSourceSetDirectory/sample/AppClass.kt".toCanonicalPaths(), absolutePath = true) }

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
                    "app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toCanonicalPaths(),
                    absolutePath = false,
                )
            }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory${sep}sample${sep}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
