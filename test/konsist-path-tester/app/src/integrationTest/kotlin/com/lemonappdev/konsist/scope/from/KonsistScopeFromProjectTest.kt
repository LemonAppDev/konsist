package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistScopeFromProjectTest {
    @Test
    fun `scopeFromProject ignoreBuildConfig true`() {
        // given
        val sut = Konsist
            .scopeFromProject(ignoreBuildConfig = true)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromTest.kt",
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
                "$rootTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
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
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromTest.kt",
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
                "$rootTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
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
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromTest.kt",
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
                "$rootTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
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
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/KonsistRootProjectPathTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromTest.kt",
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
