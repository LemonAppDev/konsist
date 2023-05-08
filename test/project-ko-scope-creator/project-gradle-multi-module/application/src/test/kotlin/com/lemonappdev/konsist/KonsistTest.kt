package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTest {
    @Test
    fun `scopeFromProject`() {
        // given
        val sut = Konsist
            .scopeFromProject()
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTest.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for library module`() {
        // given
        val sut = Konsist
            .scopeFromProject(module = "library")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(sourceSet = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(sourceSet = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/konsist/KonsistTest.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for application module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(module = "application", sourceSet = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for application module and test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(module = "application", sourceSet = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/konsist/KonsistTest.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for library module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(module = "library", sourceSet = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for library module and test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(module = "library", sourceSet = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    companion object {
        private val projectRootDirectory = File("")
            .absoluteFile
            .path
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)

        private val applicationMainSourceSetDirectory = "$projectRootDirectory/application/src/main/kotlin/com/lemonappdev"

        private val applicationTestSourceSetDirectory = "$projectRootDirectory/application/src/test/kotlin/com/lemonappdev"

        private val libraryMainSourceSetDirectory = "$projectRootDirectory/library/src/main/kotlin/com/lemonappdev"

        private val libraryTestSourceSetDirectory = "$projectRootDirectory/library/src/test/kotlin/com/lemonappdev"
    }

    private fun KoScope.mapToFilePaths() = files()
        .toList()
        .map { it.filePath }
}
