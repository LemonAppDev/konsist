package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import io.kotest.assertions.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoFunctionDeclarationForKoExtensionTypeProviderTest {
    @Test
    fun `function-with-extension-type`() {
        // given
        val sut =
            getSnippetFile("function-with-extension-type")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            isExtension shouldBeEqualTo true
            extensionReceiverType?.name shouldBeEqualTo "String"
        }
    }

    @Test
    fun `function-without-extension-type`() {
        // given
        val sut =
            getSnippetFile("function-without-extension-type")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            isExtension shouldBeEqualTo false
            extensionReceiverType?.name shouldBeEqualTo null
        }
    }

    @Test
    fun `function-with-generic-extension-type`() {
        // given
        val sut =
            getSnippetFile("function-with-generic-extension-type")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            isExtension shouldBeEqualTo true
            extensionReceiverType?.name shouldBeEqualTo "List<Int>"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkoextensiontype/", fileName)
}
