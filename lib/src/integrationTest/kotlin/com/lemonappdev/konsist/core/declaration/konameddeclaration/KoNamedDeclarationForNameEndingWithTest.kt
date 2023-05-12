package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationForNameEndingWithTest {
    @Test
    fun `class-has-name-with-suffix`() {
        // given
        val sut = getSnippetFile("class-has-name-with-suffix")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("lass") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-name-with-suffix`() {
        // given
        val sut = getSnippetFile("interface-has-name-with-suffix")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("ace") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-name-with-suffix`() {
        // given
        val sut = getSnippetFile("object-has-name-with-suffix")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("ect") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-name-with-suffix`() {
        // given
        val sut = getSnippetFile("function-has-name-with-suffix")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("ion") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-name-with-suffix`() {
        // given
        val sut = getSnippetFile("property-has-name-with-suffix")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("rty") shouldBeEqualTo true
            hasNameEndingWith("non") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornameendingwith/", fileName)
}
