package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toNormalizedPaths
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistScopeFromModuleTest {
    @Test
    fun `scopeFromModule for app module`() {
        // given
        val sut = Konsist
            .scopeFromModule("app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/koclass/KoClassDeclarationForHasTestTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/kofile/KoFileForModuleName.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/kofile/KoFileForSourceSetName.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ).toNormalizedPaths(),
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
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toNormalizedPaths(),
        )
    }

    @Test
    fun `scopeFromModule for app and data modules`() {
        // given
        val sut = Konsist
            .scopeFromModule("app", "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/koclass/KoClassDeclarationForHasTestTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/kofile/KoFileForModuleName.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/kofile/KoFileForSourceSetName.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/scope/from/KonsistScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toNormalizedPaths(),
        )
    }
}
