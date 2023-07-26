package com.lemonappdev.konsist.container.koscope.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KonsistScopeFromTest {
    @Test
    fun `scopeFromTest`() {
        // given
        val sut = Konsist
            .scopeFromTest()
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
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
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
    fun `scopeFromTest, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(sourceSetName = "test")
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
    fun `scopeFromTest, app module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app")
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
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
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
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, root module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }
}
