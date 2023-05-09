package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataTestSourceSetDirectory
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
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromModule.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
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
    fun `scopeFromTest, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromModule.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
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
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, app module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromModule.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, app module, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(moduleName = "app", sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromTest, app module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromModule.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProjectFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, app module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromTest, data module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromTest, data module, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(moduleName = "data", sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromTest, data module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromTest, data module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "data", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }
}
