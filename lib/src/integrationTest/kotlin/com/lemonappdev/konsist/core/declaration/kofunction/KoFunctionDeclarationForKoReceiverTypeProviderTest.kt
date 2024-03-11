package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoReceiverTypeProviderTest {
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
            hasReceiverType { it.name == "Int" } shouldBeEqualTo false
            hasReceiverTypeOf(Int::class) shouldBeEqualTo false
            hasReceiverType("Int") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-with-type-receiver`() {
        // given
        val sut = getSnippetFile("function-with-type-receiver")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            receiverType?.name shouldBeEqualTo "Int"
            hasReceiverType() shouldBeEqualTo true
            hasReceiverType { it.name == "Int" } shouldBeEqualTo true
            hasReceiverType { it.name == "String" } shouldBeEqualTo false
            hasReceiverTypeOf(Int::class) shouldBeEqualTo true
            hasReceiverTypeOf(String::class) shouldBeEqualTo false
            hasReceiverType("Int") shouldBeEqualTo true
            hasReceiverType("String") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkoreceivertypeprovider/", fileName)
}
