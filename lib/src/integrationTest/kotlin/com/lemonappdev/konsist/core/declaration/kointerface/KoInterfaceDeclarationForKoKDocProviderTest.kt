package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoKDocProviderTest {
    @Test
    fun `interface-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("interface-without-kdoc")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("interface-with-kdoc")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("interface-with-one-line-kdoc")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkokdocprovider/", fileName)
}
