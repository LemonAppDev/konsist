package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.util.KotlinFileParser
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class ChildrenIndexTest {
    @TempDir
    private lateinit var tempDir: File

    @Test
    fun `should return direct children`() {
        // given
        val file = getKoFile(HIERARCHY)
        val sut = ChildrenIndex(file.classes() + file.interfaces() + file.objects())
        val sampleInterface = file.interfaces().first { it.name == "SampleInterface" }

        // when
        val result = sut.getChildren(sampleInterface, indirectChildren = false)

        // then
        result.map { it.name } shouldBeEqualTo listOf("SampleClass", "SampleChildInterface", "SampleObject")
    }

    @Test
    fun `should return direct and indirect children in the order of declarations`() {
        // given
        val file = getKoFile(HIERARCHY)
        val sut = ChildrenIndex(file.classes() + file.interfaces() + file.objects())
        val sampleInterface = file.interfaces().first { it.name == "SampleInterface" }

        // when
        val result = sut.getChildren(sampleInterface, indirectChildren = true)

        // then
        result.map { it.name } shouldBeEqualTo
            listOf("SampleClass", "SampleChildClass", "SampleGrandchildClass", "SampleChildInterface", "SampleObject")
    }

    @Test
    fun `should return children of cyclic hierarchy`() {
        // given
        val file = getKoFile("package com.app\n\nopen class SampleClass : OtherClass()\n\nopen class OtherClass : SampleClass()\n")
        val sut = ChildrenIndex(file.classes())
        val sampleClass = file.classes().first { it.name == "SampleClass" }

        // when
        val result = sut.getChildren(sampleClass, indirectChildren = true)

        // then
        result.map { it.name } shouldBeEqualTo listOf("SampleClass", "OtherClass")
    }

    @Test
    fun `should return empty list when declaration has no children`() {
        // given
        val file = getKoFile(HIERARCHY)
        val sut = ChildrenIndex(file.classes() + file.interfaces() + file.objects())
        val sampleObject = file.objects().first()

        // when
        val result = sut.getChildren(sampleObject, indirectChildren = true)

        // then
        result shouldBeEqualTo emptyList()
    }

    private fun getKoFile(content: String): KoFileDeclaration =
        KotlinFileParser.getKoFile(File(tempDir, "SampleFile.kt").apply { writeText(content) })

    companion object {
        private val HIERARCHY =
            """
            package com.app

            interface SampleInterface

            open class SampleClass : SampleInterface

            open class SampleChildClass : SampleClass()

            class SampleGrandchildClass : SampleChildClass()

            interface SampleChildInterface : SampleInterface

            object SampleObject : SampleInterface
            """.trimIndent()
    }
}
