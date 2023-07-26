package com.lemonappdev.konsist.container.koscope

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider.rootTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistSliceTest {
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
}
