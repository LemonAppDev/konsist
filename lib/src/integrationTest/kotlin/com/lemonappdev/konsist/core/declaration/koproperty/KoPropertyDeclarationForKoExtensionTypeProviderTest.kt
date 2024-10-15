package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import io.kotest.assertions.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoPropertyDeclarationForKoExtensionTypeProviderTest {
    @Test
    fun `property-with-extension-type`() {
        // given
        val sut =
            getSnippetFile("property-with-extension-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            isExtension shouldBeEqualTo true
            extensionReceiverType?.name shouldBeEqualTo "String"
        }
    }

    @Test
    fun `property-without-extension-type`() {
        // given
        val sut =
            getSnippetFile("property-without-extension-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            isExtension shouldBeEqualTo false
            extensionReceiverType?.name shouldBeEqualTo null
        }
    }

    @Test
    fun `property-with-generic-extension-type`() {
        // given
        val sut =
            getSnippetFile("property-with-generic-extension-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            isExtension shouldBeEqualTo true
            extensionReceiverType?.name shouldBeEqualTo "List<Int>"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkoextensiontype/", fileName)
}
