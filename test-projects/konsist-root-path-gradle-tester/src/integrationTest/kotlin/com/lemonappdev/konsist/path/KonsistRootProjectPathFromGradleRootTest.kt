package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathFromGradleRootTest {
    @Disabled
    @Test
    fun `project root path resolved from Gradle root`() {
        val projectRootPath = File("")
            .absoluteFile
            .path

        // then
        Konsist.projectRootPath shouldBeEqualTo projectRootPath
    }
}
