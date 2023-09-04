package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoArgumentNameProviderTest {
    @Test
    fun `argument-in-enum-const-without-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-without-argument-name")
            .classes()
            .enumConstants
            .arguments
            .first()

        // then
        assertSoftly (sut) {
            value shouldBeEqualTo "0"
            argumentName shouldBeEqualTo null
        }
    }

    @Test
    fun `argument-in-enum-const-with-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-with-argument-name")
            .classes()
            .enumConstants
            .arguments
            .first()

        // then
        assertSoftly (sut) {
            value shouldBeEqualTo "0"
            argumentName shouldBeEqualTo "sampleArgument"
        }
    }

    @Test
    fun `argument-in-annotation-without-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-without-argument-name")
            .functions()
            .annotations
            .arguments
            .first()

        // then
        assertSoftly (sut) {
            value shouldBeEqualTo "text"
            argumentName shouldBeEqualTo null
        }
    }

    @Test
    fun `argument-in-annotation-with-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-with-argument-name")
            .functions()
            .annotations
            .arguments
            .first()

        // then
        assertSoftly (sut) {
            value shouldBeEqualTo "text"
            argumentName shouldBeEqualTo "sampleParameter"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkoargumentnameprovider/", fileName)
}
