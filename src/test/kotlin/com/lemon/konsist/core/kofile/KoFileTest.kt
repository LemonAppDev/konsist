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
        sut.apply {
            hasClass("SampleClass") shouldBeEqualTo true
            hasClass("OtherClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-property`() {
        // given
        val sut = getSut("file-with-package-and-property")
            .files()
            .first()

        // then
        sut.apply {
            hasProperty("sampleProperty") shouldBeEqualTo true
            hasProperty("otherProperty") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-function`() {
        // given
        val sut = getSut("file-with-package-and-function")
            .files()
            .first()

        // then
        sut.apply {
            hasFunction("sampleFunction") shouldBeEqualTo true
            hasFunction("otherFunction") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-object`() {
        // given
        val sut = getSut("file-with-package-and-object")
            .files()
            .first()

        // then
        sut.apply {
            hasObject("SampleObject") shouldBeEqualTo true
            hasObject("OtherObject") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-with-package-and-interface`() {
        // given
        val sut = getSut("file-with-package-and-interface")
            .files()
            .first()

        // then
        sut.apply {
            hasInterface("SampleInterface") shouldBeEqualTo true
            hasInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forfile/$fileName.kt.txt")
}
