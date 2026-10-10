package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
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
}
