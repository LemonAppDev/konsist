package com.lemonappdev.konsist.core.declaration.konameddeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNamedDeclarationForNameStartingWithTest {
    @Test
    fun `class-has-name-with-prefix`() {
        // given
        val sut = getSnippetFile("class-has-name-with-prefix")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("Sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-name-with-prefix`() {
        // given
        val sut = getSnippetFile("interface-has-name-with-prefix")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("Sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-name-with-prefix`() {
        // given
        val sut = getSnippetFile("object-has-name-with-prefix")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("Sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-name-with-prefix`() {
        // given
        val sut = getSnippetFile("function-has-name-with-prefix")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-name-with-prefix`() {
        // given
        val sut = getSnippetFile("property-has-name-with-prefix")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("sam") shouldBeEqualTo true
            hasNameStartingWith("non") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/konameddeclaration/snippet/fornamestartingwith/", fileName)
}
