package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationForNameMatchingTest {
    @Test
    fun `class-has-name-matching`() {
        // given
        val sut = getSnippetFile("class-has-name-matching")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-name-matching`() {
        // given
        val sut = getSnippetFile("interface-has-name-matching")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-name-matching`() {
        // given
        val sut = getSnippetFile("object-has-name-matching")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-name-matching`() {
        // given
        val sut = getSnippetFile("function-has-name-matching")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-name-matching`() {
        // given
        val sut = getSnippetFile("property-has-name-matching")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornamematching/", fileName)
}
