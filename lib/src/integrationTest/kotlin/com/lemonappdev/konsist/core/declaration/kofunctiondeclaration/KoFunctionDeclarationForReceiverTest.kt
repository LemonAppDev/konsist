package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForReceiverTest {
    @Test
    fun `function-without-receiver`() {
        // given
        val sut = getSnippetFile("function-without-receiver")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            receiver shouldBeEqualTo null
            hasReceiver() shouldBeEqualTo false
            hasReceiver("Int") shouldBeEqualTo false
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
            receiver?.name shouldBeEqualTo "Int"
            hasReceiver() shouldBeEqualTo true
            hasReceiver("Int") shouldBeEqualTo true
            hasReceiver("String") shouldBeEqualTo false
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
            receiver?.name shouldBeEqualTo "SampleClass"
            hasReceiver() shouldBeEqualTo true
            hasReceiver("SampleClass") shouldBeEqualTo true
            hasReceiver("String") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forreceiver/", fileName)
}
