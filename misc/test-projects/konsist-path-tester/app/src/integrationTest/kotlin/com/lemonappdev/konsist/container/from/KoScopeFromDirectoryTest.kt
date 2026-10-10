package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromDirectoryTest {

    @Test
    fun `scopeFromDirectory`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/fixture/")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectory accepts windows separators`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("""app\src\main\kotlin\com\lemonappdev\fixture\""")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromExternalDirectory accepts unix and windows separators`() {
        // given
        val unixPath = "$appMainSourceSetDirectory/fixture/".replace("\\", "/")
        val windowsPath = "$appMainSourceSetDirectory/fixture/".replace("/", "\\")

        // when
        val unixSut = Konsist.scopeFromExternalDirectory(unixPath).mapToFilePaths()
        val windowsSut = Konsist.scopeFromExternalDirectory(windowsPath).mapToFilePaths()

        // then
        val expected =
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator()
        unixSut shouldBeEqualTo expected
        windowsSut shouldBeEqualTo expected
    }

    @Test
    fun `scopeFromDirectory throws exception if path does not exist`() {
        // given
        val func =
            { Konsist.scopeFromDirectory("app/src/main/kotlin/com/lemonappdev/nonExisting/") }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${fileSeparator}nonExisting$fileSeparator"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectory throws exception if path points to file`() {
        // given
        val func =
            {
                Konsist.scopeFromDirectory(
                    "app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt",
                )
            }

        // then
        val message =
            "Path is a file, but should be a directory: $appMainSourceSetDirectory${fileSeparator}fixture${fileSeparator}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
