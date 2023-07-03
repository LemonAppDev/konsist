package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForReceiverTypeTest {
    @Test
    fun `property-without-receiver-type`() {
        // given
        val sut = getSnippetFile("property-without-receiver-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            receiverType shouldBeEqualTo null
            hasReceiverType() shouldBeEqualTo false
            hasReceiverType("Int") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-receiver-type`() {
        // given
        val sut = getSnippetFile("property-with-receiver-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            receiverType?.name shouldBeEqualTo "Int"
            hasReceiverType() shouldBeEqualTo true
            hasReceiverType("Int") shouldBeEqualTo true
            hasReceiverType("String") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forreceivertype/", fileName)
}
