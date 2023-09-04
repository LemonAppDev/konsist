package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoArgumentNameProviderTest {
    @Test
    fun `argument-without-argument-name`() {
        // given
        val sut = getSnippetFile("argument-without-argument-name")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        assertSoftly (sut) {
            value shouldBeEqualTo "0"
            argumentName shouldBeEqualTo null
        }
    }

    @Test
    fun `argument-with-argument-name`() {
        // given
        val sut = getSnippetFile("argument-with-argument-name")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        assertSoftly (sut) {
            value shouldBeEqualTo "0"
            argumentName shouldBeEqualTo "sampleArgument"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkoargumentnameprovider/", fileName)
}
