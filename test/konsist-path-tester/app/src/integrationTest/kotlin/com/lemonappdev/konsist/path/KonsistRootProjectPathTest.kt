package com.lemonappdev.konsist.path

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KonsistRootProjectPathTest {
    private val projectRootPath by lazy {
        File("")
            .absoluteFile
            .path
            .dropLastWhile { it != '/' }
            .dropLastWhile { it != '/' }
            .dropLast(1)
    }

    @Test
    fun `projectRootPath`() {
        // then
        Konsist.rootProjectPath shouldBeEqualTo projectRootPath
    }

    @Test
    fun `rootProjectDirectory is equal to projectRootPath`() {
        // then
        Konsist.rootProjectDirectory shouldBeEqualTo File(projectRootPath)
    }

    @Test
    fun `rootProjectDirectory exists`() {
        // then
        Konsist.rootProjectDirectory.exists() shouldBeEqualTo true
    }

    @Test
    fun `rootProjectDirectory isDirectory`() {
        // then
        Konsist.rootProjectDirectory.isDirectory shouldBeEqualTo true
    }
}
