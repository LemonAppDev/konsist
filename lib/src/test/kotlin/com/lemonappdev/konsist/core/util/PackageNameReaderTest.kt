package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class PackageNameReaderTest {
    @Test
    fun `should read package name`() {
        // given
        val text = "package com.app.feature\n\nimport com.app.Other\n\nclass SampleClass\n"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo "com.app.feature"
    }

    @Test
    fun `should return empty name for file without package directive`() {
        // given
        val text = "import com.app.Other\n\nclass SampleClass\n"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo ""
    }

    @Test
    fun `should return empty name for empty file`() {
        // when
        val result = PackageNameReader.getPackageName("", isWholeText = true)

        // then
        result shouldBeEqualTo ""
    }

    @Test
    fun `should ignore package keyword in comments`() {
        // given
        val text = "/* package com.wrong1 */\n// package com.wrong2\n/** package com.wrong3 */\npackage com.app\n"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo "com.app"
    }

    @Test
    fun `should read package name declared after file annotations`() {
        // given
        val text =
            """
            @file:Suppress("package com.wrong")
            @file:UseSerializers(DateSerializer::class)

            package com.app
            """.trimIndent()

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo "com.app"
    }

    @Test
    fun `should read package name ending with semicolon`() {
        // given
        val text = "package com.app;\nclass SampleClass\n"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo "com.app"
    }

    @Test
    fun `should read package name containing backticks`() {
        // given
        val text = "package com.`fun`.app\n"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo "com.fun.app"
    }

    @Test
    fun `should read package name of file starting with utf-8 bom`() {
        // given
        val text = "\uFEFFpackage com.app\n"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = true)

        // then
        result shouldBeEqualTo "com.app"
    }

    @Test
    fun `should return null when beginning of file ends before package name is known`() {
        // then
        PackageNameReader.getPackageName("package com.ap", isWholeText = false) shouldBeEqualTo null
        PackageNameReader.getPackageName("/* license", isWholeText = false) shouldBeEqualTo null
        PackageNameReader.getPackageName("pack", isWholeText = false) shouldBeEqualTo null
    }

    @Test
    fun `should read package name from beginning of file`() {
        // given
        val text = "package com.app\n\nimport com.ap"

        // when
        val result = PackageNameReader.getPackageName(text, isWholeText = false)

        // then
        result shouldBeEqualTo "com.app"
    }

    @Test
    fun `should read package name declared after long comment`(
        @TempDir tempDir: File,
    ) {
        // given
        val license = "// License line\n".repeat(1000)
        val file = File(tempDir, "SampleClass.kt").apply { writeText("${license}package com.app\n\nclass SampleClass\n") }

        // when
        val result = PackageNameReader.getPackageName(file)

        // then
        result shouldBeEqualTo "com.app"
    }

    @Test
    fun `should return null when file does not exist`(
        @TempDir tempDir: File,
    ) {
        // given
        val file = File(tempDir, "NonExisting.kt")

        // when
        val result = PackageNameReader.getPackageName(file)

        // then
        result shouldBeEqualTo null
    }
}
