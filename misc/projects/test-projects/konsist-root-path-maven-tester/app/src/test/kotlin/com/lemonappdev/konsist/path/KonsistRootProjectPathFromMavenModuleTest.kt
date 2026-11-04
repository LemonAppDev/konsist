package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAbsolutePath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromMavenModuleTest {
    @Test
    fun `project root path resolved from Maven module`() {
        val projectRootPath =
            File("")
                .absoluteFile
                .path
                .replace(File.separator, "/")
                .dropLastWhile { it != '/' }
                .dropLast(1)
                .replace("/", File.separator)

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `withAbsolutePath resolved from Maven module`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .withAbsolutePath("${Konsist.projectRootPath}/src/..")
                .map { it.name }

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromMavenRootTest")
    }

    @Test
    fun `residesInPath with absolutePath true resolved from Maven module`() {
        // given
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .first { it.name == "KonsistRootProjectPathFromMavenModuleTest" }

        // then
        sut.residesInPath("${Konsist.projectRootPath}/app/..", absolutePath = true) shouldBeEqualTo true
        sut.residesInPath("${Konsist.projectRootPath}/src/..", absolutePath = true) shouldBeEqualTo false
    }
}
