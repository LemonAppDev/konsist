package com.lemonappdev.konsist.scope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootTestSourceSetDirectory
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
            ),
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
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/koclass/KoClassDeclarationForHasTestTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/kofile/KoFileForContainingSourceSetName.kt",
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
            ),
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
            ),
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
            ),
        )
    }
}
