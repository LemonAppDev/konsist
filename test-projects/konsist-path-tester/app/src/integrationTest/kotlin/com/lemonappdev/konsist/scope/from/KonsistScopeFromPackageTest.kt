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

class KonsistScopeFromPackageTest {
    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, app module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, app module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "app", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appMainSourceSetDirectory/sample/AppClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, app module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, app module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, data module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, data module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "data", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, data module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, data module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "data", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", sourceSetName = "main")
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
    fun `scopeFromPackage for any__sample__any package, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, app module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$appMainSourceSetDirectory/sample/AppClass.kt",
                "$appMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, app module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "app", sourceSetName = "main")
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
    fun `scopeFromPackage for any__sample__any package, app module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, app module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, data module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, data module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "data", sourceSetName = "main")
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
    fun `scopeFromPackage for any__sample__any package, data module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, data module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "data", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }
}
