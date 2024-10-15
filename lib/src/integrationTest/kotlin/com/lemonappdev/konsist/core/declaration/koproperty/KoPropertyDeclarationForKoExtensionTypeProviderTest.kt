package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
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
        sut.isExtension shouldBeEqualTo true
    }

    @Test
    fun `property-without-extension-type`() {
        // given
        val sut =
            getSnippetFile("property-without-extension-type")
                .properties()
                .first()

        // then
        sut.isExtension shouldBeEqualTo false
    }

    @Test
    fun `property-with-generic-extension-type`() {
        // given
        val sut =
            getSnippetFile("property-with-generic-extension-type")
                .properties()
                .first()

        // then
        sut.isExtension shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoextensiontype/", fileName)
}
