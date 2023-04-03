package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileTest {
    @Test
    fun `file-with-import`() {
        // given
        val sut = getSut("file-with-import")
            .files()
            .first()

        // then
        sut
            .imports
            .map { it.name } shouldBeEqualTo listOf("com.sampleimport")
    }

    @Test
    fun `file-without-import`() {
        // given
        val sut = getSut("file-without-import")
            .files()
            .first()

        // then
        sut
            .imports
            .isEmpty()
    }

    @Test
    fun `file-with-package`() {
        // given
        val sut = getSut("file-with-package")
            .files()
            .first()

        // then
        sut.packageDirective?.name shouldBeEqualTo "SamplePackage"
    }

    @Test
    fun `file-without-package`() {
        // given
        val sut = getSut("file-without-package")
            .files()
            .first()

        // then
        sut.packageDirective shouldBeEqualTo null
    }

    @Test
    fun `file-with-package-and-class`() {
        // given
        val sut = getSut("file-with-package-and-class")
            .files()
            .first()

        // then
        with(sut) {
            containsClass("SampleClass") shouldBeEqualTo true
            containsClass("OtherClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-property`() {
        // given
        val sut = getSut("file-with-package-and-property")
            .files()
            .first()

        // then
        with(sut) {
            containsProperty("sampleProperty") shouldBeEqualTo true
            containsProperty("otherProperty") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-function`() {
        // given
        val sut = getSut("file-with-package-and-function")
            .files()
            .first()

        // then
        with(sut) {
            containsFunction("sampleFunction") shouldBeEqualTo true
            containsFunction("otherFunction") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-object`() {
        // given
        val sut = getSut("file-with-package-and-object")
            .files()
            .first()

        // then
        with(sut) {
            containsObject("SampleObject") shouldBeEqualTo true
            containsObject("OtherObject") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-interface`() {
        // given
        val sut = getSut("file-with-package-and-interface")
            .files()
            .first()

        // then
        with(sut) {
            containsInterface("SampleInterface") shouldBeEqualTo true
            containsInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/$fileName.kt.txt")
}
