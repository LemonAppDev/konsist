package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationForNameContainingTest {
    @Test
    fun `class-has-name-containing`() {
        // given
        val sut = getSnippetFile("class-has-name-containing")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-name-containing`() {
        // given
        val sut = getSnippetFile("interface-has-name-containing")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-name-containing`() {
        // given
        val sut = getSnippetFile("object-has-name-containing")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-name-containing`() {
        // given
        val sut = getSnippetFile("function-has-name-containing")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-name-containing`() {
        // given
        val sut = getSnippetFile("property-has-name-containing")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("ple") shouldBeEqualTo true
            hasNameContaining("non") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornamecontaining/", fileName)
}
