package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromGradleRootTest {
    @Test
    fun `project root path resolved from Gradle root`() {
        // given
        val projectRootPath =
            File("")
                .absoluteFile
                .path

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `scopeFromProject resolved from Gradle root contains files from all modules`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .map { it.name }
                .sorted()

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromGradleModuleTest", "KonsistRootProjectPathFromGradleRootTest")
    }
}
