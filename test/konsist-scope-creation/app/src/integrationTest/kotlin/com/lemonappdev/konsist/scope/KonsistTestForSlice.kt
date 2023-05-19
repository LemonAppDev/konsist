package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForSlice {
    @Test
    fun `slice-with-predicate-name`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "test")

        // then
        val actual = sut.slice { it.name.startsWith("RootClass") }

        actual
            .mapToFilePaths()
            .shouldBeEqualTo(
                listOf(
                    "${rootTestSourceSetDirectory}/sample/RootClassTest.kt",
                ),
            )
    }

    @Test
    fun `slice-with-predicate-import`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.sample", sourceSetName = "main")

        // then
        val actual = sut.slice { it.hasImports("com.sample") }

        actual
            .mapToFilePaths()
            .shouldBeEqualTo(
                listOf(
                    "$dataMainSourceSetDirectory/sample/LibClass.kt",
                ),
            )
    }
}
