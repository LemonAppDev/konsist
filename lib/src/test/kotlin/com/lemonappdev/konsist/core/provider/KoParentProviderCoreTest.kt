package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.core.util.KotlinFileParser
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.io.TempDir
import java.io.File

class KoParentProviderCoreTest {
    @TempDir
    private lateinit var tempDir: File

    @Test
    @Timeout(10)
    fun `should return indirect parents of deep hierarchy`() {
        // given
        val depth = 40
        val classes = (1..depth).joinToString("\n") { "open class SampleClass$it : SampleClass${it - 1}()" }
        val sut =
            getKoFile("package com.app\n\nopen class SampleClass0\n$classes\n")
                .classes()
                .first { it.name == "SampleClass$depth" }

        // when
        val result = sut.parents(indirectParents = true)

        // then
        result.map { it.name } shouldBeEqualTo (depth - 1 downTo 0).map { "SampleClass$it" }
    }

    @Test
    fun `should return indirect parents of cyclic hierarchy`() {
        // given
        val sut =
            getKoFile("package com.app\n\nopen class SampleClass : OtherClass()\n\nopen class OtherClass : SampleClass()\n")
                .classes()
                .first { it.name == "SampleClass" }

        // when
        val result = sut.parents(indirectParents = true)

        // then
        result.map { it.name } shouldBeEqualTo listOf("OtherClass", "SampleClass")
    }

    private fun getKoFile(content: String) = KotlinFileParser.getKoFile(File(tempDir, "SampleFile.kt").apply { writeText(content) })
}
