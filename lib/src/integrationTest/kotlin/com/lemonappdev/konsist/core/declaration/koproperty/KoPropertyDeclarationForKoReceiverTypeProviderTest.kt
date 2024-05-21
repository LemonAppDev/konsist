package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoReceiverTypeProviderTest {
    @Test
    fun `property-without-receiver-type`() {
        // given
        val sut =
            getSnippetFile("property-without-receiver-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            receiverType shouldBeEqualTo null
            hasReceiverType() shouldBeEqualTo false
            hasReceiverType { it.name == "Int" } shouldBeEqualTo false
            hasReceiverTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-receiver-type`() {
        // given
        val sut =
            getSnippetFile("property-with-receiver-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            receiverType?.name shouldBeEqualTo "SampleClass"
            hasReceiverType() shouldBeEqualTo true
            hasReceiverType { it.name == "SampleClass" } shouldBeEqualTo true
            hasReceiverType { it.name == "String" } shouldBeEqualTo false
            hasReceiverTypeOf(SampleClass::class) shouldBeEqualTo true
            hasReceiverTypeOf(String::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkoreceivertypeprovider/", fileName)
}
