package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeFromModulesTest {
    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromModules(set) for app module`() {
        // given
        val moduleNames = setOf("app")

        val sut = Konsist
            .scopeFromModules(moduleNames)
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
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromModules(list) for app module`() {
        // given
        val moduleNames = listOf("app")

        val sut = Konsist
            .scopeFromModules(moduleNames)
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
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromModules(set) for data module`() {
        // given
        val moduleNames = setOf("data")

        val sut = Konsist
            .scopeFromModules(moduleNames)
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
    fun `scopeFromModules(list) for data module`() {
        // given
        val moduleNames = listOf("data")

        val sut = Konsist
            .scopeFromModules(moduleNames)
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
    fun `scopeFromModules(set) for root module`() {
        // given
        val moduleNames = setOf("root")

        val sut = Konsist
            .scopeFromModules(moduleNames)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/build/RootBuildClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
                "$rootMainSourceSetDirectory/fixture/target/RootTargetClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromModules(list) for root module`() {
        // given
        val moduleNames = listOf("root")

        val sut = Konsist
            .scopeFromModules(moduleNames)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/build/RootBuildClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
                "$rootMainSourceSetDirectory/fixture/target/RootTargetClass.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromModules(set) for app and data modules`() {
        // given
        val moduleNames = setOf("app", "data")

        val sut = Konsist
            .scopeFromModules(moduleNames)
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
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromModules(list) for app and data modules`() {
        // given
        val moduleNames = listOf("app", "data")

        val sut = Konsist
            .scopeFromModules(moduleNames)
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
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }
}
