package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.core.util.KotlinFileParser
import com.lemonappdev.konsist.core.util.PackageNameReader
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class ProjectDeclarationIndexTest {
    @TempDir
    private lateinit var tempDir: File

    private val parsedFileNames = mutableListOf<String>()

    @Test
    fun `should parse only files of packages that can contain the type`() {
        // given
        val sut =
            createIndex(
                "SampleClass.kt" to "package com.app.feature\n\nclass SampleClass\n",
                "OtherClass.kt" to "package com.app.other\n\nclass OtherClass\n",
            )

        // when
        val result = sut.getTypeDeclaration("com.app.feature.SampleClass")

        // then
        (result as? KoNameProvider)?.name shouldBeEqualTo "SampleClass"
        parsedFileNames shouldBeEqualTo listOf("SampleClass.kt")
    }

    @Test
    fun `should find nested type`() {
        // given
        val sut = createIndex("SampleClass.kt" to "package com.app\n\nclass SampleClass {\n    class NestedClass\n}\n")

        // when
        val result = sut.getTypeDeclaration("com.app.SampleClass.NestedClass")

        // then
        (result as? KoNameProvider)?.name shouldBeEqualTo "NestedClass"
    }

    @Test
    fun `should find type declared in root package`() {
        // given
        val sut = createIndex("SampleClass.kt" to "class SampleClass\n")

        // when
        val result = sut.getTypeDeclaration("SampleClass")

        // then
        (result as? KoNameProvider)?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `should not parse files when no file is declared in package that can contain the type`() {
        // given
        val sut = createIndex("SampleClass.kt" to "package com.app\n\nclass SampleClass\n")

        // when
        val result = sut.getTypeDeclaration("com.other.SampleClass")

        // then
        result shouldBeEqualTo null
        parsedFileNames shouldBeEqualTo emptyList()
    }

    @Test
    fun `should parse files of package once`() {
        // given
        val sut = createIndex("SampleClass.kt" to "package com.app\n\nclass SampleClass\n\nclass OtherClass\n")

        // when
        sut.getTypeDeclaration("com.app.SampleClass")
        sut.getTypeDeclaration("com.app.OtherClass")

        // then
        parsedFileNames shouldBeEqualTo listOf("SampleClass.kt")
    }

    @Test
    fun `should return class when type alias with the same fully qualified name is declared before`() {
        // given
        val sut =
            createIndex(
                "SampleType.kt" to "package com.app\n\ntypealias SampleType = String\n",
                "SampleTypeActual.kt" to "package com.app\n\nclass SampleType\n",
            )

        // when
        val result = sut.getTypeDeclaration("com.app.SampleType")

        // then
        result shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `should find top-level function`() {
        // given
        val sut = createIndex("SampleFunction.kt" to "package com.app\n\nfun sampleFunction() {}\n")

        // when
        val result = sut.getDeclaration("com.app.sampleFunction")

        // then
        result shouldBeInstanceOf KoFunctionDeclaration::class
    }

    @Test
    fun `should not return file annotation with the fully qualified name of annotation class`() {
        // given
        val sut =
            createIndex(
                "SampleFile.kt" to "@file:SampleAnnotation\n\npackage com.app\n\nimport com.app.SampleAnnotation\n",
            )

        // when
        val result = sut.getDeclaration("com.app.SampleAnnotation")

        // then
        result shouldBeEqualTo null
    }

    private fun createIndex(vararg files: Pair<String, String>): ProjectDeclarationIndex {
        val projectFiles = files.map { (name, content) -> File(tempDir, name).apply { writeText(content) } }

        return ProjectDeclarationIndex(
            getFiles = { projectFiles },
            getPackageName = { PackageNameReader.getPackageName(it) },
            getKoFile = {
                parsedFileNames += it.name
                KotlinFileParser.getKoFile(it)
            },
        )
    }
}
