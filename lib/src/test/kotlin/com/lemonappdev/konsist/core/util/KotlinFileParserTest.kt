package com.lemonappdev.konsist.core.util

import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.io.File

class KotlinFileParserTest {
    @Test
    fun `should use unix separators in error message for windows path`() {
        // given
        val file = File("""C:\Users\user\project\src\main\kotlin\com\app\NonExisting.kt""")
        val func = { KotlinFileParser.getKoFile(file) }

        // then
        func shouldThrow IllegalArgumentException::class withMessage
            "File must be a Kotlin file: C:/Users/user/project/src/main/kotlin/com/app/NonExisting.kt"
    }
}
