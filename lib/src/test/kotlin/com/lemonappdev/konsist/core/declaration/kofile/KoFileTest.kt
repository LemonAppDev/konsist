package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileTest {
    @Test
    fun `file-name`() {
        // given
        val sut = getSnippetFile("file-name")
            .files()
            .first()

        // then
        sut.name shouldBeEqualTo "file-name.kt"
    }

    @Test
    fun `file-path`() {
        // given
        val sut = getSnippetFile("file-path")
            .files()
            .first()

        // then
        sut
            .path
            .endsWith("/lib/src/test/kotlin/com/lemonappdev/konsist/core/declaration/kofile/snippet/file-path.kt")
            .shouldBeEqualTo(true)
    }

    @Test
    fun `file-has-path`() {
        // given
        val sut = getSnippetFile("file-has-path")
            .files()
            .first()

        // then
        sut.run {
            resideInPath("../declaration/kofile/..") shouldBeEqualTo true
            resideInPath("/declaration/kofile/") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-project-path`() {
        // given
        val sut = getSnippetFile("file-project-path")
            .files()
            .first()

        // then
        sut.projectPath shouldBeEqualTo "/lib/src/test/kotlin/com/lemonappdev/konsist/core/declaration/kofile/snippet/file-project-path.kt"
    }

    @Test
    fun `file-contains-imports`() {
        // given
        val sut = getSnippetFile("file-contains-imports")
            .files()
            .first()

        // then
        sut
            .imports
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleAnnotation",
                    "com.lemonappdev.konsist.testdata.SampleType",
                ),
            )
    }

    @Test
    fun `file-has-imports`() {
        // given
        val sut = getSnippetFile("file-has-imports")
            .files()
            .first()

        // then
        sut.run {
            hasImport() shouldBeEqualTo true
            hasImport("com..") shouldBeEqualTo true
            hasImport("com") shouldBeEqualTo false
            hasImport("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImport("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSnippetFile("file-contains-no-import")
            .files()
            .first()

        // then
        sut.run {
            imports.isEmpty() shouldBeEqualTo true
            hasImport() shouldBeEqualTo false
            hasImport("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSnippetFile("file-contains-package")
            .files()
            .first()

        // then
        sut.run {
            packageDirective?.name shouldBeEqualTo "samplepackage"
            resideInPackage("com.samplepackage") shouldBeEqualTo true
            resideInPackage("com..") shouldBeEqualTo true
            resideInPackage("com") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSnippetFile("file-contains-no-package")
            .files()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo null
    }

    @Test
    fun `file-contains-package-and-class`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-class")
            .files()
            .first()

        // then
        sut.run {
            containsClass("SampleClass") shouldBeEqualTo true
            containsClass("OtherClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package-and-property`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-property")
            .files()
            .first()

        // then
        sut.run {
            containsProperty("sampleProperty") shouldBeEqualTo true
            containsProperty("otherProperty") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package-and-function`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-function")
            .files()
            .first()

        // then
        sut.run {
            containsFunction("sampleFunction") shouldBeEqualTo true
            containsFunction("otherFunction") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package-and-object`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-object")
            .files()
            .first()

        // then
        sut.run {
            containsObject("SampleObject") shouldBeEqualTo true
            containsObject("OtherObject") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package-and-interface`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-interface")
            .files()
            .first()

        // then
        sut.run {
            containsInterface("SampleInterface") shouldBeEqualTo true
            containsInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-annotation`() {
        // given
        val sut = getSnippetFile("file-contains-annotation")
            .files()
            .first()

        // then
        sut.run {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation")
            hasAnnotation("SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("SampleAnnotation1") shouldBeEqualTo false
            hasAnnotation("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo true
            hasAnnotation("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation>() shouldBeEqualTo true
            hasAnnotation<SampleAnnotation1>() shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-annotation`() {
        // given
        val sut = getSnippetFile("file-contains-no-annotation")
            .files()
            .first()

        // then
        sut.run {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotation("SampleAnnotation") shouldBeEqualTo false
            hasAnnotation("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
            hasAnnotation<SampleAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-typealias")
            .files()
            .first()

        // then
        sut
            .typeAliases
            .first()
            .run {
                name shouldBeEqualTo "SampleTypeAlias"
                type.sourceType shouldBeEqualTo "() -> Int"
            }
    }

    @Test
    fun `file-has-typealias`() {
        // given
        val sut = getSnippetFile("file-has-typealias")
            .files()
            .first()

        // then
        sut.run {
            hasTypeAlias() shouldBeEqualTo true
            hasTypeAlias("SampleTypeAlias") shouldBeEqualTo true
            hasTypeAlias("OtherTypeAlias") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-no-typealias")
            .files()
            .first()

        // then
        sut
            .typeAliases
            .isEmpty()
            .shouldBeEqualTo(true)
    }

    @Test
    fun `file-has-no-typealias`() {
        // given
        val sut = getSnippetFile("file-has-no-typealias")
            .files()
            .first()

        // then
        sut.run {
            hasTypeAlias() shouldBeEqualTo false
            hasTypeAlias("SampleTypeAlias") shouldBeEqualTo false
        }
    }

    @Test
    fun `files-are-equal`() {
        // given
        val file1 = getSnippetFile("files-are-equal")
            .files()
            .first()

        val file2 = getSnippetFile("files-are-equal")
            .files()
            .first()

        // then
        file1 shouldBeEqualTo file2
        file1.hashCode() shouldBeEqualTo file2.hashCode()
    }

    @Test
    fun `files-are-not-equal`() {
        // given
        val file1 = getSnippetFile("files-are-not-equal")
            .files()
            .first()

        val file2 = getSnippetFile("files-are-equal")
            .files()
            .first()

        // then
        file1 shouldNotBeEqualTo file2
        file1.hashCode() shouldNotBeEqualTo file2.hashCode()
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofile/snippet/", fileName)
}
