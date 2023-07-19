package com.lemonappdev.konsist.api.ext.provider.koreceivertype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasReceiverTypeOf
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReceiverTypeProviderExtTest {
    @Test
    fun `function-has-receiver-with-simple-type`() {
        // given
        val sut = getSnippetFile("function-has-receiver-with-simple-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReceiverTypeOf<Int>() shouldBeEqualTo true
            hasReceiverTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-receiver-with-complex-type`() {
        // given
        val sut = getSnippetFile("function-has-receiver-with-complex-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReceiverTypeOf<SampleClass>() shouldBeEqualTo true
            hasReceiverTypeOf<Int>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koreceivertype/snippet/", fileName)
}
