package com.lemonappdev.konsist.core.declaration.kofiledeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationTest {
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
        assertSoftly(sut) {
            hasImports() shouldBeEqualTo true
            hasImports("com..") shouldBeEqualTo true
            hasImports("com..", "..testdata..") shouldBeEqualTo true
            hasImports("com") shouldBeEqualTo false
            hasImports("com", "..testdata..") shouldBeEqualTo false
            hasImports("com.lemonappdev.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSnippetFile("file-contains-no-import")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            imports.isEmpty() shouldBeEqualTo true
            hasImports() shouldBeEqualTo false
            hasImports("com.lemonappdev.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSnippetFile("file-contains-package")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            packagee?.name shouldBeEqualTo "samplepackage"
            hasPackage("com.samplepackage") shouldBeEqualTo true
            hasPackage("com..") shouldBeEqualTo true
            hasPackage("com") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSnippetFile("file-contains-no-package")
            .files()
            .first()

        // then
        sut.packagee shouldBeEqualTo null
    }

    @Test
    fun `file-contains-package-and-class`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-class")
            .files()
            .first()

        // then
        assertSoftly(sut) {
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
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
        assertSoftly(sut) {
            containsInterface("SampleInterface") shouldBeEqualTo true
            containsInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-annotations`() {
        // given
        val sut = getSnippetFile("file-contains-annotations")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation1", "SampleAnnotation2")
            hasAnnotations("SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("SampleAnnotation1", "SampleAnnotation2") shouldBeEqualTo true
            hasAnnotations("OtherAnnotation") shouldBeEqualTo false
            hasAnnotations("SampleAnnotation1", "OtherAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation1") shouldBeEqualTo true
            hasAnnotations("com.lemonappdev.konsist.testdata.OtherAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-annotations-with-KClass`() {
        // given
        val sut = getSnippetFile("file-contains-annotations-with-KClass")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationsOf(SampleAnnotation1::class) shouldBeEqualTo true
            hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) shouldBeEqualTo true
            hasAnnotationsOf(NonExistingAnnotation::class) shouldBeEqualTo false
            hasAnnotationsOf(SampleAnnotation1::class, NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-annotation`() {
        // given
        val sut = getSnippetFile("file-contains-no-annotation")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotations("SampleAnnotation") shouldBeEqualTo false
            hasAnnotations("com.lemonappdev.konsist.testdata.SampleAnnotation") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-typealias")
            .files()
            .first()

        // then
        assertSoftly(
            sut
                .typeAliases
                .first(),
        ) {
            name shouldBeEqualTo "SampleTypeAlias"
            type.sourceType shouldBeEqualTo "() -> Int"
        }
    }

    @Test
    fun `file-has-typealiases`() {
        // given
        val sut = getSnippetFile("file-has-typealiases")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo true
            hasTypeAliases("OtherTypeAlias") shouldBeEqualTo false
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
        assertSoftly(sut) {
            hasTypeAliases() shouldBeEqualTo false
            hasTypeAliases("SampleTypeAlias") shouldBeEqualTo false
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofiledeclaration/snippet/", fileName)
}
