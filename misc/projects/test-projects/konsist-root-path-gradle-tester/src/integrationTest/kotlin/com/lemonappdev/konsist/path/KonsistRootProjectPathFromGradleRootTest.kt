package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAbsolutePath
import com.lemonappdev.konsist.api.ext.list.withoutAbsolutePath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromGradleRootTest {
    @Test
    fun `project root path resolved from Gradle root`() {
        val projectRootPath = File("")
            .absoluteFile
            .path

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `withAbsolutePath resolved from Gradle root`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .withAbsolutePath("${Konsist.projectRootPath}/app/..")
                .map { it.name }

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromGradleModuleTest")
    }

    @Test
    fun `withoutAbsolutePath resolved from Gradle root`() {
        // when
        val sut =
            Konsist
                .scopeFromProject()
                .files
                .withoutAbsolutePath("${Konsist.projectRootPath}/app/..")
                .map { it.name }

        // then
        sut shouldBeEqualTo listOf("KonsistRootProjectPathFromGradleRootTest")
    }
}
