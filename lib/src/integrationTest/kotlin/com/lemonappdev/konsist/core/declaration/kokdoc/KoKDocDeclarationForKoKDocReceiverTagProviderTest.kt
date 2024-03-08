package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.RECEIVER
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoKDocReceiverTagProviderTest {
    @Test
    fun `kdoc-without-receiver-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-receiver-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.receiverTag shouldBeEqualTo null
            it?.hasReceiverTag shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-receiver-tag`() {
        // given
        val sut =
            getSnippetFile("function-with-receiver-tag")
                .functions(includeNested = true)
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.receiverTag?.name shouldBeEqualTo RECEIVER
            it?.receiverTag?.description shouldBeEqualTo "sample receiver description"
            it?.hasReceiverTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocreceivertagprovider/", fileName)
}
