package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTestForScopeFromTest {
    @Test
    fun `scopeFromTest`() {
        // given
        val sut = Konsist
            .scopeFromTest()
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, application module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "application")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, application module, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(moduleName = "application", sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromTest, application module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "application", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, library module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "library")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, library module, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(moduleName = "library", sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromTest, library module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "library", sourceSetName = "test")
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
