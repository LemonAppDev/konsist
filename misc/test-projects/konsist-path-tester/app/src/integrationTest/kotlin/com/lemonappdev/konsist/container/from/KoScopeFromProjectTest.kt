package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromProjectTest {
    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromProject ignoreBuildConfig true`() {
        // given
        val sut = Konsist
            .scopeFromProject(ignoreBuildConfig = true)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoriesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModulesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetsTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/AbsolutePathTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromProject ignoreBuildConfig false`() {
        // given
        val sut = Konsist
            .scopeFromProject(ignoreBuildConfig = false)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoriesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModulesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetsTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/AbsolutePathTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$projectRootDirectory/buildSrc/RootBuildScrKotlinClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ).toOsSeparator(),
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
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProject for root module`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ).toOsSeparator(),
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
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromProject for integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoriesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModulesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetsTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/AbsolutePathTest.kt",
            ).toOsSeparator(),
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
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ).toOsSeparator(),
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
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromProject for app module and integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoriesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModulesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetsTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/path/AbsolutePathTest.kt",
            ).toOsSeparator(),
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
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
            ).toOsSeparator(),
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
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProject for root module and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = "root", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProject for data module with gradle project path and main source set`() {
        // given
        val sut = Konsist
            .scopeFromProject(moduleName = ":data", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromProject throws exception if module does not exist`() {
        // given
        val func = { Konsist.scopeFromProject(moduleName = ":nonExisting") }

        // then
        val message = "Module does not exist: ':nonExisting'. Directory not found (module names are case-sensitive): " +
            "$projectRootDirectory/nonExisting".toOsSeparator()
        func shouldThrow IllegalArgumentException::class withMessage message
    }
}
