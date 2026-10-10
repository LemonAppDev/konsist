package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
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
}
