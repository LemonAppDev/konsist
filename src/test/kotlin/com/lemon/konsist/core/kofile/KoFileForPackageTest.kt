package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPackageTest {
    @Test
    fun `file-with-one-package`() {
        // given
        val sut =
            getSut("file-with-one-package")
                .files()
                .first()

        // then
        sut.packageDirective?.name shouldBeEqualTo "SamplePackage"
    }

    @Test
    fun `file-without-package`() {
        // given
        val sut =
            getSut("file-without-package")
                .files()
                .first()

        // then
        sut.packageDirective shouldBeEqualTo null
    }

    @Test
    fun `package-with-class-inside`() {
        // given
        val sut =
            getSut("package-with-class-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasClass("SampleClass") shouldBeEqualTo true
            hasClass("OtherClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `package-with-property-inside`() {
        // given
        val sut =
            getSut("package-with-property-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasProperty("sampleProperty") shouldBeEqualTo true
            hasProperty("otherProperty") shouldBeEqualTo false
        }
    }

    @Test
    fun `package-with-function-inside`() {
        // given
        val sut =
            getSut("package-with-function-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasFunction("sampleFunction") shouldBeEqualTo true
            hasFunction("otherFunction") shouldBeEqualTo false
        }
    }

    @Test
    fun `package-with-object-inside`() {
        // given
        val sut =
            getSut("package-with-object-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasObject("SampleObject") shouldBeEqualTo true
            hasObject("OtherObject") shouldBeEqualTo false
        }
    }

    @Test
    fun `package-with-interface-inside`() {
        // given
        val sut =
            getSut("package-with-interface-inside")
                .files()
                .first()

        // then
        sut.apply {
            hasInterface("SampleInterface") shouldBeEqualTo true
            hasInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forpackage/$fileName.kt.txt")
}
