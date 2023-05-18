package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.ext.mapToFilePaths
import com.lemonappdev.konsist.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistTestForOperator {
    @Test
    fun `plus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
        val scope2 = Konsist.scopeFromProject(moduleName = "data")

        // when
        val sut = (scope1 + scope2)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataMainSourceSetDirectory/sample/LibClass.kt",
                "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
            ),
        )
    }

    @Test
    fun `minus operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
        val scope2 = Konsist.scopeFromProject(moduleName = "data")

        // when
        val sut = (scope1 - scope2)
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$rootTestSourceSetDirectory/sample/RootClassTest.kt"
            ),
        )
    }

    @Test
    fun `minus operator works when we subtract element which scope1 not contain`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
        val scope2 = Konsist.scopeFromProject(sourceSetName = "integrationTest")

        // when
        val sut = (scope1 - scope2)
            .mapToFilePaths()

        // then
        val expected = scope1.mapToFilePaths()
        sut shouldBeEqualTo expected
    }

    @Test
    fun `plusAssign operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
        val scope2 = Konsist.scopeFromProject(moduleName = "data")

        // when
        scope1 += scope2

        // then
        scope1
            .mapToFilePaths()
            .shouldBeEqualTo(
                listOf(
                    "$dataMainSourceSetDirectory/sample/LibClass.kt",
                    "$dataMainSourceSetDirectory/sample/data/LibDataClass.kt",
                    "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                    "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                    "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                    "$rootTestSourceSetDirectory/sample/RootClassTest.kt",
                ),
            )
    }

    @Test
    fun `minusAssign operator`() {
        // given
        val scope1 = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")
        val scope2 = Konsist.scopeFromProject(moduleName = "data")

        // when
        scope1 -= scope2

        // then
        scope1
            .mapToFilePaths()
            .shouldBeEqualTo(
                listOf(
                    "$rootTestSourceSetDirectory/sample/RootClassTest.kt"
                ),
            )
    }
}
