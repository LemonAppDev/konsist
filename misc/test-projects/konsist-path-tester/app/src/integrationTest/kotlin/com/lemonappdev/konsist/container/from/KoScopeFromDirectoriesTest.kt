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
            "app/src/main/kotlin/com/lemonappdev/sample/".toOsSeparator(),
            "app/src/integrationTest/kotlin/com/lemonappdev/sample/".toOsSeparator(),
        )
        val sut = Konsist
            .scopeFromDirectories(paths)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectories(list)`() {
        // given
        val paths = listOf(
            "app/src/main/kotlin/com/lemonappdev/sample/".toOsSeparator(),
            "app/src/integrationTest/kotlin/com/lemonappdev/sample/".toOsSeparator(),
        )
        val sut = Konsist
            .scopeFromDirectories(paths)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromDirectories(set) throws exception if path does not exist`() {
        // given
        val paths = setOf("app/src/main/kotlin/com/lemonappdev/nonExisting/".toOsSeparator())

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${fileSeparator}nonExisting$fileSeparator"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectories(list) throws exception if path does not exist`() {
        // given
        val paths = listOf("app/src/main/kotlin/com/lemonappdev/nonExisting/".toOsSeparator())

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message = "Directory does not exist: $appMainSourceSetDirectory${fileSeparator}nonExisting$fileSeparator"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectories(set) throws exception if path points to file`() {
        // given
        val paths = setOf("app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toOsSeparator())

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message =
            "Path is a file, but should be a directory: $appMainSourceSetDirectory${fileSeparator}sample${fileSeparator}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromDirectories(list) throws exception if path points to file`() {
        // given
        val paths = listOf("app/src/main/kotlin/com/lemonappdev/sample/AppClass.kt".toOsSeparator())

        val func = { Konsist.scopeFromDirectories(paths) }

        // then
        val message =
            "Path is a file, but should be a directory: $appMainSourceSetDirectory${fileSeparator}sample${fileSeparator}AppClass.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
