package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationTest {

    @Test
    fun `class`() {
        // given
        val sut = getSnippetFile("class")
            .classes()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `class-has-name-sth`() {
        // given
        val sut = getSnippetFile("class-has-name-sth")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
            hasNameStartingWith("Sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
            hasNameEndingWith("lass") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface`() {
        // given
        val sut = getSnippetFile("interface")
            .interfaces()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `interface-has-name-sth`() {
        // given
        val sut = getSnippetFile("interface-has-name-sth")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
            hasNameStartingWith("Sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
            hasNameEndingWith("ace") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `object`() {
        // given
        val sut = getSnippetFile("object")
            .objects()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `object-has-name-sth`() {
        // given
        val sut = getSnippetFile("object-has-name-sth")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
            hasNameStartingWith("Sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
            hasNameEndingWith("ect") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `function`() {
        // given
        val sut = getSnippetFile("function")
            .functions()
            .first()

        // then
        sut.name shouldBeEqualTo "sampleFunction"
    }

    @Test
    fun `function-has-name-sth`() {
        // given
        val sut = getSnippetFile("function-has-name-sth")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
            hasNameStartingWith("sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
            hasNameEndingWith("ion") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `property`() {
        // given
        val sut = getSnippetFile("property")
            .properties()
            .first()

        // then
        sut.name shouldBeEqualTo "sampleProperty"
    }

    @Test
    fun `property-has-name-sth`() {
        // given
        val sut = getSnippetFile("property-has-name-sth")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
            hasNameStartingWith("sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
            hasNameEndingWith("rty") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/", fileName)
}
