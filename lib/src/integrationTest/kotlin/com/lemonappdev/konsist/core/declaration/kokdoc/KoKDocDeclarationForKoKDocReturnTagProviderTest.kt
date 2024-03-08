package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoKDocReturnTagProviderTest {
    @Test
    fun `kdoc-without-return-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-return-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.returnTag shouldBeEqualTo null
            it?.hasReturnTag shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-return-tag`() {
        // given
        val sut =
            getSnippetFile("function-with-return-tag")
                .functions(includeNested = true)
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.returnTag?.name shouldBeEqualTo RETURN
            it?.returnTag?.description shouldBeEqualTo "The result of the computation."
            it?.hasReturnTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocreturntagprovider/", fileName)
}
