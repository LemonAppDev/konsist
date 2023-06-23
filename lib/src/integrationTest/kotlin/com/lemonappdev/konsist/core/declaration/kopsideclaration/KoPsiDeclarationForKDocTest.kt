package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForKDocTest {
    @Test
    fun `declaration-with-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-with-kdoc")
            .declarations()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `declaration-with-one-line-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-with-one-line-kdoc")
            .declarations()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `declaration-without-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-without-kdoc")
            .declarations()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forkdoc/", fileName)
}
