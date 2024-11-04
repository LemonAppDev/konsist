package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoIsGenericProviderTest {
    @Test
    fun `class-is-not-generic`() {
        // given
        val sut =
            getSnippetFile("class-is-not-generic")
                .classes()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo false
    }

    @Test
    fun `class-is-generic`() {
        // given
        val sut =
            getSnippetFile("class-is-generic")
                .classes()
                .first()

        // then
        sut.isGeneric shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koclass/snippet/forkoisgenericprovider/", fileName)
}
