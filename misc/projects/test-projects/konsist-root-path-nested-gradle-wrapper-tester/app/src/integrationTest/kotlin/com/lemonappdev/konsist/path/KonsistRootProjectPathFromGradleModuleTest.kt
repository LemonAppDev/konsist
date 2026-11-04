package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromGradleModuleTest {
    @Test
    fun `project root path resolved from Gradle module with own Gradle wrapper`() {
        // given
        val projectRootPath =
            File("")
                .absoluteFile
                .parentFile
                .path

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `scopeFromProject resolved from Gradle module with own Gradle wrapper contains files from all modules`() {
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
