package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

class KotlinFileParserTest {
    @Test
    fun `should use os separators in error message`() {
        // given
        val path = listOf("project", "src", "main", "kotlin", "com", "app", "NonExisting.kt").joinToString(File.separator)
        val func = { KotlinFileParser.getKoFile(File(path)) }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "File must be a Kotlin file: $path"
    }

    @Test
    fun `should parse package of file starting with utf-8 bom`(
        @TempDir tempDir: File,
    ) {
        // given
        val file = File(tempDir, "SampleClass.kt").apply { writeText("\uFEFFpackage com.app\n\nclass SampleClass\n") }

        // when
        val sut = KotlinFileParser.getKoFile(file)

        // then
        sut.packagee?.name shouldBeEqualTo "com.app"
    }

    @ParameterizedTest
    @MethodSource("provideLineEndings")
    fun `should return location and text of class in file with non-unix line endings`(
        lineEnding: String,
        @TempDir tempDir: File,
    ) {
        // given
        val file =
            File(tempDir, "SampleClass.kt").apply {
                writeText(listOf("package com.app", "", "class SampleClass {", "    val a = 1", "}", "").joinToString(lineEnding))
            }

        // when
        val sut = KotlinFileParser.getKoFile(file).classes().first()

        // then
        sut.location shouldBeEqualTo "${file.path}:3:1"
        sut.text shouldBeEqualTo "class SampleClass {\n    val a = 1\n}"
    }

    @Test
    fun `should return location of class in file with carriage return before windows line ending`(
        @TempDir tempDir: File,
    ) {
        // given
        val file = File(tempDir, "SampleClass.kt").apply { writeText("package com.app\r\r\nclass SampleClass\r\n") }

        // when
        val sut = KotlinFileParser.getKoFile(file).classes().first()

        // then
        sut.location shouldBeEqualTo "${file.path}:3:1"
        sut.text shouldBeEqualTo "class SampleClass"
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideLineEndings() =
            listOf(
                arguments("\r\n"),
                arguments("\r"),
            )
    }
}
