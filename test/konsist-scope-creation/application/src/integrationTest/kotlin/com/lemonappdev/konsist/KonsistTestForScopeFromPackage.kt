package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.applicationIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.applicationMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.libraryMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.libraryTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForScopeFromPackage {
    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
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
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
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
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
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
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, application module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "application")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, application module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "application", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, application module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "application", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, application module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "application", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, library module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "library")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, library module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "library", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, library module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "library", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for com_lemonappdev_sample package, library module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "library", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
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
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootMainSourceSetDirectory/sample/RootClass.kt",
                "$rootMainSourceSetDirectory/sample/data/RootDataClass.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
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
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
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
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
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
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                "$rootTestSourceSetDirectory/sample/data/RootDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, application module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "application")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, application module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "application", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, application module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "application", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$applicationIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, application module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "application", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, library module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "library")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, library module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "library", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, library module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "library", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromPackage for any__sample__any package, library module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..sample..", moduleName = "library", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }
}
