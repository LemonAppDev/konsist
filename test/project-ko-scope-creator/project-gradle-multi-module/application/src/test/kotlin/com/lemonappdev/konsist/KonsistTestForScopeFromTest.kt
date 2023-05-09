package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.util.PathProvider.applicationTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.libraryTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

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
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromTest, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
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
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/konsist/util/PathProvider.kt",
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
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationTestSourceSetDirectory/konsist/util/PathProvider.kt",
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

    private fun KoScope.mapToFilePaths() = files()
        .toList()
        .map { it.filePath }
}
