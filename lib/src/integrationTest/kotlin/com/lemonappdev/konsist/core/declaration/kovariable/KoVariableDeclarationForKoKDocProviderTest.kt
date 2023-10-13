package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoKDocProviderTest {
    @Test
    fun `variable-without-kdoc`() {
        // given
        val sut = getSnippetFile("variable-without-kdoc")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-with-kdoc`() {
        // given
        val sut = getSnippetFile("variable-with-kdoc")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    @Test
    fun `variable-with-one-line-kdoc`() {
        // given
        val sut = getSnippetFile("variable-with-one-line-kdoc")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkokdocprovider/", fileName)
}
