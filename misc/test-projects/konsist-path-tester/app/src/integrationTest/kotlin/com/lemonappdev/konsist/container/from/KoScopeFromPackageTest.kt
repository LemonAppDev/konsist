package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeFromPackageTest {
    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, app module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, app module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "app", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, app module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, app module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, data module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, data module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "data", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, data module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, data module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "data", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, root module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_fixture package, root module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.fixture", moduleName = "root", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", sourceSetName = "main")
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
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, app module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, app module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "app", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/fixture/AppClass.kt",
                "$appMainSourceSetDirectory/fixture/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, app module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/fixture/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/fixture/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, app module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, data module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, data module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "data", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/fixture/LibClass.kt",
                "$dataMainSourceSetDirectory/fixture/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, data module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, data module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "data", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/fixture/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/fixture/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, root module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__fixture__any package, root module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..fixture..", moduleName = "root", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootMainSourceSetDirectory/fixture/RootClass.kt",
                "$rootMainSourceSetDirectory/fixture/data/RootDataClass.kt",
                "$rootMainSourceSetDirectory/fixture/src/RootSrcClass.kt",
            ),
        )
    }
}
