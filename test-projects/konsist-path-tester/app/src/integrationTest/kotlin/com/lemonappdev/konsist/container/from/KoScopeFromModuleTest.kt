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

class KoScopeFromModuleTest {
    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromModule for app module`() {
        // given
        val sut = Konsist
            .scopeFromModule("app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromModule for data module`() {
        // given
        val sut = Konsist
            .scopeFromModule("data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromModule for root module`() {
        // given
        val sut = Konsist
            .scopeFromModule("root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/sample/src/RootSrcClass.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromModule for app and data modules`() {
        // given
        val sut = Konsist
            .scopeFromModule("app", "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }
}
