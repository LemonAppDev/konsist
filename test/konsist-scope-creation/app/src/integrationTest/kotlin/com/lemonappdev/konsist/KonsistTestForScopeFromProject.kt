package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.projectRootDirectory
import com.lemonappdev.konsist.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForScopeFromProject {
    @Test
    fun `scopeFromProject ignoreBuildConfig true`() {
        // given
        val sut = Konsist
            .scopeFromProject(ignoreBuildConfig = true)
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
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject ignoreBuildConfig false`() {
        // given
        val sut = Konsist
            .scopeFromProject(ignoreBuildConfig = false)
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
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromSourceSet.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForToString.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$projectRootDirectory/buildSrc/RootBuildScrKotlinClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for data module`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
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
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
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
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForRootProjectPath.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromDirectory.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromFile.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromModule.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromPackage.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProduction.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/KonsistTestForScopeFromProject.kt",
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
    fun `scopeFromProject for test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(sourceSetName = "test")
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
    fun `scopeFromProject for app module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "app", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for app module and integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "app", sourceSetName = "integrationTest")
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
    fun `scopeFromProject for app module and test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromProject for data module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "data", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromProject for data module and integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromProject for data module and test source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "data", sourceSetName = "test")
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
