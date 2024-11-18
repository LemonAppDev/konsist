package com.lemonappdev.konsist.core.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class HyperlinkUtilTest {

    @Test
    fun `should add prefix when path does not have it`() {
        // Given
        val path = "src/main/kotlin/com/lemonappdev/sample/AppClass.kt"
        val absolutePath = File(path).absolutePath
        val expected = "file://$absolutePath"

        // When
        val result = HyperlinkUtil.toHyperlink(path)

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `should not add prefix when path already has it`() {
        // Given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/sample/sampleFile.kt").absolutePath
        val prefixedPath = "file://$absolutePath"

        // When
        val result = HyperlinkUtil.toHyperlink(prefixedPath)

        // Then
        assertEquals(prefixedPath, result)
    }

    @Test
    fun `should handle empty path correctly`() {
        // Given
        val path = ""
        val expected = "file://${File("").absolutePath}"

        // When
        val result = HyperlinkUtil.toHyperlink(path)

        // Then
        assertEquals(expected, result)
    }
    @Test
    fun `should handle absolute path without prefix`() {
        // Given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/sample/sampleFile.kt").absolutePath
        val expected = "file://$absolutePath"

        // When
        val result = HyperlinkUtil.toHyperlink(absolutePath)

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `should handle file prefix for an absolute path`() {
        // Given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/sample/sampleFile.kt").absolutePath
        val pathWithPrefix = "file://$absolutePath"

        // When
        val result = HyperlinkUtil.toHyperlink(pathWithPrefix)

        // Then
        assertEquals(pathWithPrefix, result)
    }
}
