package com.lemonappdev.konsist.container

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeTest {
    @Test
    fun `slice-with-predicate-name`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")

        // then
        val actual = sut.slice { it.name.startsWith("RootClass") }

        actual
            .mapToFilePaths()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `toString method`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("${PathProvider.appMainSourceSetProjectDirectory}/sample/".toOsSeparator())
            .toString()

        // then
        sut shouldBeEqualTo """
            ${PathProvider.appMainSourceSetDirectory}/sample/AppClass.kt
            ${PathProvider.appMainSourceSetDirectory}/sample/data/AppDataClass.kt
        """
            .trimIndent()
            .toOsSeparator()
    }

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
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
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
            emptyList(),
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
                    "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                    "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                    "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
                ).toOsSeparator(),
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
                emptyList(),
            )
    }
}
