package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoKDocProviderTest {
    @Test
    fun `typealias-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("typealias-without-kdoc")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("typealias-with-kdoc")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("typealias-with-one-line-kdoc")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kotypealias/snippet/forkokdocprovider/", fileName)
}
