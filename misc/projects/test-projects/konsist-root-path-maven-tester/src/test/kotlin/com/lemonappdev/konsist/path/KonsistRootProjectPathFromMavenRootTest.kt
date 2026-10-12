package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAbsolutePath
import com.lemonappdev.konsist.api.ext.list.withoutAbsolutePath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromMavenRootTest {
    @Test
    fun `project root path resolved from Maven root`() {
        val projectRootPath = File("")
            .absoluteFile
            .path

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `withAbsolutePath resolved from Maven root`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .withAbsolutePath("${Konsist.projectRootPath}/app/..")
                .map { it.name }

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromMavenModuleTest")
    }

    @Test
    fun `withoutAbsolutePath resolved from Maven root`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .withoutAbsolutePath("${Konsist.projectRootPath}/app/..")
                .map { it.name }

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromMavenRootTest")
    }
}
