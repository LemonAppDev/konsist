package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoKDocProviderTest {
    @Test
    fun `property-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("property-without-kdoc")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("property-with-kdoc")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `property-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("property-with-one-line-kdoc")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkokdocprovider/", fileName)
}
