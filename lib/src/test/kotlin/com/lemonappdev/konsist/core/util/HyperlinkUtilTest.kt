package com.lemonappdev.konsist.core.util

import org.amshove.kluent.internal.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class HyperlinkUtilTest {
    @Test
    fun `should add prefix when path does not have it`() {
        // given
        val path = "src/main/kotlin/com/lemonappdev/sample/AppClass.kt"
        val absolutePath = File(path).absolutePath
        val expected = "file://$absolutePath"

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should not add prefix when path already has it`() {
        // given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/sample/sampleFile.kt").absolutePath
        val prefixedPath = "file://$absolutePath"

        // when
        val result = HyperlinkUtil.toHyperlink(prefixedPath)

        // then
        assertEquals(prefixedPath, result)
    }

    @Test
    fun `should handle empty path correctly`() {
        // given
        val path = ""
        val expected = "file://${File("").absolutePath}"

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should handle absolute path without prefix`() {
        // given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/sample/sampleFile.kt").absolutePath
        val expected = "file://$absolutePath"

        // when
        val result = HyperlinkUtil.toHyperlink(absolutePath)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should handle file prefix for an absolute path`() {
        // given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/sample/sampleFile.kt").absolutePath
        val pathWithPrefix = "file://$absolutePath"

        // when
        val result = HyperlinkUtil.toHyperlink(pathWithPrefix)

        // then
        assertEquals(pathWithPrefix, result)
    }
}
