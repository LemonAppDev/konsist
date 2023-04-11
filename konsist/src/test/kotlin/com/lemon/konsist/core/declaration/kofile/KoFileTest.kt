package com.lemon.konsist.core.declaration.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileTest {
    @Test
    fun `file-name`() {
        // given
        val sut = getSut("file-name")
            .files()
            .first()

        // then
        sut.run {
            name shouldBeEqualTo "file-name.kt"
        }
    }

    @Test
    fun `file-path`() {
        // given
        val sut = getSut("file-path")
            .files()
            .first()

        // then
        sut.run {
            path.endsWith("/konsist/src/test/kotlin/com/lemon/konsist/core/declaration/kofile/snippet/file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `file-contains-import`() {
        // given
        val sut = getSut("file-contains-import")
            .files()
            .first()

        // then
        sut.run {
            imports.map { it.name } shouldBeEqualTo listOf("com.lemon.konsist.testdata.SampleType")
            hasImport("com.lemon.konsist.testdata.SampleType") shouldBeEqualTo true
            hasImport("com.lemon.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSut("file-contains-no-import")
            .files()
            .first()

        // then
        sut.run {
            imports.isEmpty() shouldBeEqualTo true
            hasImport("com.lemon.konsist.testdata.OtherImport") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-package`() {
        // given
        val sut = getSut("file-contains-package")
            .files()
            .first()

        // then
        sut.packageDirective?.name shouldBeEqualTo "SamplePackage"
    }

    @Test
    fun `file-contains-no-package`() {
        // given
        val sut = getSut("file-contains-no-package")
            .files()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo null
    }

    @Test
    fun `file-contains-package-and-class`() {
        // given
        val sut = getSut("file-contains-package-and-class")
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
        val sut = getSut("file-contains-package-and-property")
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
        val sut = getSut("file-contains-package-and-function")
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
        val sut = getSut("file-contains-package-and-object")
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
        val sut = getSut("file-contains-package-and-interface")
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
        val sut = getSut("file-contains-annotation")
            .files()
            .first()

        // then
        sut.run {
            annotations.map { it.type } shouldBeEqualTo listOf("SampleAnnotation")
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-no-annotation`() {
        // given
        val sut = getSut("file-contains-no-annotation")
            .files()
            .first()

        // then
        sut.run {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSut("file-contains-typealias")
            .files()
            .first()

        // then
        sut.run {
            typeAliases.first().name shouldBeEqualTo "SampleTypeAlias"
            typeAliases.first().type shouldBeEqualTo "() -> Int"
        }
    }

    @Test
    fun `file-contains-no-typealias`() {
        // given
        val sut = getSut("file-contains-no-typealias")
            .files()
            .first()

        // then
        sut
            .typeAliases
            .isEmpty() shouldBeEqualTo true
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kofile/snippet/", fileName)
}
