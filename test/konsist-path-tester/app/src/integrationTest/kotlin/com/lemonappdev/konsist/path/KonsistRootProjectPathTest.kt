package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathTest {
    @Test
    fun `scopeFromProject`() {
        // given
        val projectRootPath = File("")
            .absoluteFile
            .path
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)

        // then
        Konsist.rootProjectPath shouldBeEqualTo projectRootPath
    }
}
