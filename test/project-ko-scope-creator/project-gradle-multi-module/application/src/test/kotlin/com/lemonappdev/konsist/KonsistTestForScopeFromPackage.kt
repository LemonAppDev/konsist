package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.io.File

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
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$libraryMainSourceSetDirectory/sample/LibClass.kt",
                "$libraryTestSourceSetDirectory/sample/LibClassTest.kt",
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
                "$applicationMainSourceSetDirectory/sample/AppClass.kt",
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
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
    fun `scopeFromPackage for com_lemonappdev_sample package, application module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("com.lemonappdev.sample", moduleName = "application", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/sample/AppClassTest.kt",
            ),
        )
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
    fun `scopeFromPackage for any__data__any package`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, application module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", moduleName = "application")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, application module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", moduleName = "application", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationMainSourceSetDirectory/sample/data/AppDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, application module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", moduleName = "application", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$applicationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, library module`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", moduleName = "library")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, library module, main source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", moduleName = "library", sourceSetName = "main")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryMainSourceSetDirectory/sample/data/LibDataClass.kt",
            ),
        )
    }

    @Test
    fun `scopeFromPackage for any__data__any package, library module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromPackage("..data..", moduleName = "library", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$libraryTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ),
        )
    }

    companion object {
        private val projectRootDirectory = File("")
            .absoluteFile
            .path
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)

        private val applicationMainSourceSetDirectory = "$projectRootDirectory/application/src/main/kotlin/com/lemonappdev"

        private val applicationTestSourceSetDirectory = "$projectRootDirectory/application/src/test/kotlin/com/lemonappdev"

        private val libraryMainSourceSetDirectory = "$projectRootDirectory/library/src/main/kotlin/com/lemonappdev"

        private val libraryTestSourceSetDirectory = "$projectRootDirectory/library/src/test/kotlin/com/lemonappdev"
    }

    private fun KoScope.mapToFilePaths() = files()
        .toList()
        .map { it.filePath }
}
