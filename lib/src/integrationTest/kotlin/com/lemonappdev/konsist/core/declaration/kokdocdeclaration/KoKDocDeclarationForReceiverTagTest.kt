package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoKDocTag.RECEIVER
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForReceiverTagTest {
    @Test
    fun `function-with-receiver-tag`() {
        // given
        val sut = getSnippetFile("function-with-receiver-tag")
            .functions(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.receiverTag?.name shouldBeEqualTo RECEIVER
            it?.receiverTag?.description shouldBeEqualTo "sample receiver description"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forreceivertag/", fileName)
}
