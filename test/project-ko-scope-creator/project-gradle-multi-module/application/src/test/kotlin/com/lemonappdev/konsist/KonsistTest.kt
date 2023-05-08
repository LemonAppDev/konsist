package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTest {
    @Test
    fun `scopeFromFile`() {
        // given
        val sut = Konsist
            .scopeFromFile("$applicationMainSourceSetDirectory/sample/AppClass.kt")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf("$applicationMainSourceSetDirectory/sample/AppClass.kt"),
        )
    }

    @Test
    fun `scopeFromFile throws exception if path does not exist`() {
        // given
        val func = { Konsist.scopeFromFile("$applicationMainSourceSetDirectory/sample/NonExisting.kt") }

        // then
        val message = "File does not exist: $applicationMainSourceSetDirectory/sample/NonExisting.kt"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromFile throws exception if path points to folder`() {
        // given
        val func = { Konsist.scopeFromFile("$applicationMainSourceSetDirectory/sample/") }

        // then
        val message = "Path is a directory, but should be a file: $applicationMainSourceSetDirectory/sample/"
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromPackage for any__data__any package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, application module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", module = "application")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, application module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", module = "application", sourceSet = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, application module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", module = "application", sourceSet = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, library module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", module = "library")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, library module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", module = "library", sourceSet = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, library module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", module = "library", sourceSet = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

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
