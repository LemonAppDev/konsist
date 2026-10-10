package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAbsolutePath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromGradleModuleTest {
    @Test
    fun `project root path resolved from Gradle module`() {
        val projectRootPath = File("")
            .absoluteFile
            .path
            .replace(File.separator, "/")
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)
            .replace("/", File.separator)

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `withAbsolutePath resolved from Gradle module`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .withAbsolutePath("${Konsist.projectRootPath}/src/..")
                .map { it.name }

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromGradleRootTest")
    }

    @Test
    fun `resideInPath with absolutePath true resolved from Gradle module`() {
        // given
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .first { it.name == "KonsistRootProjectPathFromGradleModuleTest" }

        // then
        sut.resideInPath("${Konsist.projectRootPath}/app/..", absolutePath = true) shouldBeEqualTo true
        sut.resideInPath("${Konsist.projectRootPath}/src/..", absolutePath = true) shouldBeEqualTo false
    }
}
