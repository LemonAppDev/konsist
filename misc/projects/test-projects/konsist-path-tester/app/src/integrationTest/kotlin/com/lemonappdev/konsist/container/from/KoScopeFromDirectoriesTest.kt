package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.fileSeparator
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromDirectoriesTest {

    @Test
    fun `scopeFromDirectories(set)`() {
        // given
        val paths = setOf(
            "app/src/main/kotlin/com/lemonappdev/fixture/",
            "app/src/integrationTest/kotlin/com/lemonappdev/fixture/",
        )
        val sut = Konsist
            .scopeFromDirectories(paths)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectories(list)`() {
        // given
        val paths = listOf(
            "app/src/main/kotlin/com/lemonappdev/fixture/",
            "app/src/integrationTest/kotlin/com/lemonappdev/fixture/",
        )
        val sut = Konsist
            .scopeFromDirectories(paths)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectories accepts windows separators`() {
        // given
        val paths = listOf(
            """app\src\main\kotlin\com\lemonappdev\fixture\""",
            """app\src\integrationTest\kotlin\com\lemonappdev\fixture\""",
        )
        val sut = Konsist
            .scopeFromDirectories(paths)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectories(set) throws exception if path does not exist`() {
        // given
        val paths = setOf("app/src/main/kotlin/com/lemonappdev/nonExisting/")

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${fileSeparator}nonExisting$fileSeparator"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectories(list) throws exception if path does not exist`() {
        // given
        val paths = listOf("app/src/main/kotlin/com/lemonappdev/nonExisting/")

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${fileSeparator}nonExisting$fileSeparator"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectories(set) throws exception if path points to file`() {
        // given
        val paths = setOf("app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt")

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message =
            "Path is a file, but should be a directory: $appMainSourceSetDirectory${fileSeparator}fixture${fileSeparator}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectories(list) throws exception if path points to file`() {
        // given
        val paths = listOf("app/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt")

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message =
            "Path is a file, but should be a directory: $appMainSourceSetDirectory${fileSeparator}fixture${fileSeparator}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
