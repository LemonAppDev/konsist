package com.lemonappdev.konsist.core.declaration.koenumconst

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstDeclarationForKoKDocProviderTest {
    @Test
    fun `enum-const-without-kdoc`() {
        // given
        val sut = getSnippetFile("enum-const-without-kdoc")
            .classes()
            .first()
            .constants
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-const-with-kdoc`() {
        // given
        val sut = getSnippetFile("enum-const-with-kdoc")
            .classes()
            .first()
            .constants
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-const-with-one-line-kdoc`() {
        // given
        val sut = getSnippetFile("enum-const-with-one-line-kdoc")
            .classes()
            .first()
            .constants
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconst/snippet/forkokdocprovider/", fileName)
}
