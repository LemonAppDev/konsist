package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.applicationIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.applicationMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.libraryMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.libraryTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForScopeFromProject {
    @Test
    fun `scopeFromProject`() {
        // given
        val sut = Konsist
            .scopeFromProject()
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for library module`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "library")
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
            .scopeFromProject(sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for application module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "application", sourceSetName = "main")
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
    fun `scopeFromProject for application module and integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "application", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$applicationIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for application module and test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "application", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromProject for library module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "library", sourceSetName = "main")
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
    fun `scopeFromProject for library module and integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "library", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromProject for library module and test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "library", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }
}
