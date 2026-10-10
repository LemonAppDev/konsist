package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS

class HyperlinkUtilTest {
    private val userDir = System.getProperty("user.dir")

    @Test
    @EnabledOnOs(OS.LINUX, OS.MAC)
    fun `should resolve relative path against working directory`() {
        // given
        val path = "src/main/kotlin/com/lemonappdev/fixture/AppClass.kt"

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file://$userDir/src/main/kotlin/com/lemonappdev/fixture/AppClass.kt"
    }

    @Test
    @EnabledOnOs(OS.LINUX, OS.MAC)
    fun `should resolve relative path with spaces against working directory`() {
        // given
        val path = "src/main/kotlin/com/lemonappdev/my fixture/App Class.kt"

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file://$userDir/src/main/kotlin/com/lemonappdev/my fixture/App Class.kt"
    }

    @Test
    @EnabledOnOs(OS.LINUX, OS.MAC)
    fun `should resolve empty path to working directory`() {
        // given
        val path = ""

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file://$userDir"
    }

    @Test
    @EnabledOnOs(OS.LINUX, OS.MAC)
    fun `should add prefix to absolute unix path`() {
        // given
        val path = "/Users/user/project/src/main/kotlin/com/app/SampleClass.kt"

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file:///Users/user/project/src/main/kotlin/com/app/SampleClass.kt"
    }

    @Test
    @EnabledOnOs(OS.LINUX, OS.MAC)
    fun `should not duplicate prefix for absolute unix path that already has it`() {
        // given
        val path = "file:///Users/user/project/src/main/kotlin/com/app/SampleClass.kt"

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file:///Users/user/project/src/main/kotlin/com/app/SampleClass.kt"
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    fun `should add prefix and unix separators to absolute windows path`() {
        // given
        val path = """C:\Users\user\project\src\main\kotlin\com\app\SampleClass.kt"""

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file:///C:/Users/user/project/src/main/kotlin/com/app/SampleClass.kt"
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    fun `should not duplicate prefix for absolute windows path that already has it`() {
        // given
        val path = """file://C:\Users\user\project\src\main\kotlin\com\app\SampleClass.kt"""

        // when
        val result = HyperlinkUtil.toHyperlink(path)

        // then
        result shouldBeEqualTo "file:///C:/Users/user/project/src/main/kotlin/com/app/SampleClass.kt"
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
}
