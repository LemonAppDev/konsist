package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForContainsMethodTest {
    @Test
    fun `file-contains-package-and-class`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-class")
            .files
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
            .files
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
            .files
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
            .files
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
            .files
            .first()

        // then
        assertSoftly(sut) {
            containsInterface("SampleInterface") shouldBeEqualTo true
            containsInterface("OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forcontainsmethod/", fileName)
}
