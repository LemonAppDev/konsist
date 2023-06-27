package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForReceiverTypeTest {
    @Test
    fun `function-without-receiver`() {
        // given
        val sut = getSnippetFile("function-without-receiver")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            receiverType shouldBeEqualTo null
            hasReceiverType() shouldBeEqualTo false
            hasReceiverType("Int") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-simple-type-receiver`() {
        // given
        val sut = getSnippetFile("function-with-simple-type-receiver")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            receiverType?.name shouldBeEqualTo "Int"
            hasReceiverType() shouldBeEqualTo true
            hasReceiverType("Int") shouldBeEqualTo true
            hasReceiverType("String") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-complex-type-receiver`() {
        // given
        val sut = getSnippetFile("function-with-complex-type-receiver")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            receiverType?.name shouldBeEqualTo "SampleClass"
            hasReceiverType() shouldBeEqualTo true
            hasReceiverType("SampleClass") shouldBeEqualTo true
            hasReceiverType("String") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forreceiver/", fileName)
}
