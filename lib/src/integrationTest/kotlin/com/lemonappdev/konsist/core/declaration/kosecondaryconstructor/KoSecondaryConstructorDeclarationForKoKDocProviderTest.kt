package com.lemonappdev.konsist.core.declaration.kosecondaryconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoKDocProviderTest {
    @Test
    fun `secondary-constructor-without-kdoc`() {
        // given
        val sut =
            getSnippetFile("secondary-constructor-without-kdoc")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-with-kdoc`() {
        // given
        val sut =
            getSnippetFile("secondary-constructor-with-kdoc")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `secondary-constructor-with-one-line-kdoc`() {
        // given
        val sut =
            getSnippetFile("secondary-constructor-with-one-line-kdoc")
                .classes()
                .first()
                .secondaryConstructors
                .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kosecondaryconstructor/snippet/forkokdocprovider/", fileName)
}
