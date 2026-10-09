package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
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
}
