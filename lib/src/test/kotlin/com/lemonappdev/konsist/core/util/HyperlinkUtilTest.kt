package com.lemonappdev.konsist.core.util

import org.amshove.kluent.internal.assertEquals
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class HyperlinkUtilTest {
    @Test
    fun `should add prefix when path does not have it`() {
        // given
        val path = "src/main/kotlin/com/lemonappdev/fixture/AppClass.kt"
        val expected = toExpectedFileUrl(File(path).absolutePath)

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should not add prefix when path already has it`() {
        // given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/fixture/fixtureFile.kt").absolutePath
        val prefixedPath = "file://$absolutePath"
        val expected = toExpectedFileUrl(absolutePath)

        // when
        val result = HyperlinkUtil.toHyperlink(prefixedPath)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should handle empty path correctly`() {
        // given
        val path = ""
        val expected = toExpectedFileUrl(File("").absolutePath)

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should handle absolute path without prefix`() {
        // given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/fixture/fixtureFile.kt").absolutePath
        val expected = toExpectedFileUrl(absolutePath)

        // when
        val result = HyperlinkUtil.toHyperlink(absolutePath)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should handle file prefix for an absolute path`() {
        // given
        val absolutePath = File("src/main/kotlin/com/lemonappdev/fixture/fixtureFile.kt").absolutePath
        val pathWithPrefix = "file://$absolutePath"
        val expected = toExpectedFileUrl(absolutePath)

        // when
        val result = HyperlinkUtil.toHyperlink(pathWithPrefix)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should create file url with three slashes and unix separators for windows path`() {
        // given
        val absolutePath = """C:\Users\user\project\src\main\kotlin\com\app\SampleClass.kt:3:1"""

        // when
        val result = HyperlinkUtil.toFileUrl(absolutePath)

        // then
        result shouldBeEqualTo "file:///C:/Users/user/project/src/main/kotlin/com/app/SampleClass.kt:3:1"
    }

    @Test
    fun `should create file url with three slashes for unix path`() {
        // given
        val absolutePath = "/Users/user/project/src/main/kotlin/com/app/SampleClass.kt:3:1"

        // when
        val result = HyperlinkUtil.toFileUrl(absolutePath)

        // then
        result shouldBeEqualTo "file:///Users/user/project/src/main/kotlin/com/app/SampleClass.kt:3:1"
    }

    @Test
    fun `should handle relative path with spaces`() {
        // given
        val path = "src/main/kotlin/com/lemonappdev/my fixture/App Class.kt"
        val expected = toExpectedFileUrl(File(path).absolutePath)

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `should keep spaces in file url for windows path with spaces`() {
        // given
        val absolutePath = """C:\Users\John Doe\my project\src\main\kotlin\com\app\SampleClass.kt:3:1"""

        // when
        val result = HyperlinkUtil.toFileUrl(absolutePath)

        // then
        result shouldBeEqualTo "file:///C:/Users/John Doe/my project/src/main/kotlin/com/app/SampleClass.kt:3:1"
    }

    @Test
    fun `should keep spaces in file url for unix path with spaces`() {
        // given
        val absolutePath = "/Users/John Doe/my project/src/main/kotlin/com/app/SampleClass.kt:3:1"

        // when
        val result = HyperlinkUtil.toFileUrl(absolutePath)

        // then
        result shouldBeEqualTo "file:///Users/John Doe/my project/src/main/kotlin/com/app/SampleClass.kt:3:1"
    }

    @Test
    fun `should create file url with four slashes for windows network path`() {
        // given
        val absolutePath = """\\server\share\project\src\main\kotlin\com\app\SampleClass.kt:3:1"""

        // when
        val result = HyperlinkUtil.toFileUrl(absolutePath)

        // then
        result shouldBeEqualTo "file:////server/share/project/src/main/kotlin/com/app/SampleClass.kt:3:1"
    }

    private fun toExpectedFileUrl(absolutePath: String) = "file:///${absolutePath.replace("\\", "/").removePrefix("/")}"
}
