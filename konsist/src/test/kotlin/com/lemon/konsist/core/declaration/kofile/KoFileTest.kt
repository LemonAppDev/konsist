package com.lemon.konsist.core.declaration.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
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
        with(sut) {
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
        with(sut) {
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
        sut
            .imports
            .map { it.name } shouldBeEqualTo listOf("com.lemon.konsist.testdata.SampleType")
    }

    @Test
    fun `file-contains-no-import`() {
        // given
        val sut = getSut("file-contains-no-import")
            .files()
            .first()

        // then
        sut
            .imports
            .isEmpty()
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
        with(sut) {
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
        with(sut) {
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
        with(sut) {
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
        with(sut) {
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
        with(sut) {
            containsInterface("SampleInterface") shouldBeEqualTo true
            containsInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("kofile/snippet/$fileName.kttxt")
}
