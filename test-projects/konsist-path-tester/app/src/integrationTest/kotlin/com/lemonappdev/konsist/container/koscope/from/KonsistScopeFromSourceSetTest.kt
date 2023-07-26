package com.lemonappdev.konsist.container.koscope.from

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

class KonsistScopeFromSourceSetTest {
    @Test
    fun `scopeFromSourceSet for main source set`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("main")
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
                "$rootMainSourceSetDirectory/sample/src/RootSrcClass.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromSourceSet for integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/kofile/KoFileForModuleName.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/kofile/KoFileForSourceSetName.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/KonsistOperatorTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/KonsistSliceTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/KonsistToStringTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/koscope/from/KonsistScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclassdeclaration/KoClassDeclarationForKoHasTestProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromSourceSet for test source set`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromSourceSet for main and test source sets`() {
        // given
        val sut = Konsist
            .scopeFromSourceSet("main", "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/sample/src/RootSrcClass.kt",
            ).toOsSeparator(),
        )
    }
}
