package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.sep
import com.lemonappdev.konsist.helper.ext.toCanonicalPaths
import com.lemonappdev.konsist.helper.ext.toCanonicalPathss
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromDirectoryTest {

    @Test
    fun `scopeFromDirectory`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/sample/".toCanonicalPaths())
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toCanonicalPathss(),
        )
    }

    @Test
    fun `scopeFromDirectory throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/".toCanonicalPaths()) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${sep}nonExisting$sep"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory throws exception if path points to file`() {
        // given
        val func =
            {
                Konsist.scopeFromDirectory(
                    "app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toCanonicalPaths(),
                )
            }

        // then
        val message = "Path is a file, but should be a directory: $appMainSourceSetDirectory${sep}sample${sep}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
