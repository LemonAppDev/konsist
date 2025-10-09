package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoNameProviderTest {
    @Test
    fun `argument-in-enum-const-without-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-without-name")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo ""
            hasName("name") shouldBeEqualTo false
            hasName("name", ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-enum-const-with-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-with-name")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleArgument"
            hasName("sampleArgument") shouldBeEqualTo true
            hasName("otherArgument") shouldBeEqualTo false
            hasName("sampleargument", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleargument", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `argument-in-annotation-without-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-without-name")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo ""
            hasName("name") shouldBeEqualTo false
            hasName("name", ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-with-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-with-name")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleParameter"
            hasName("sampleParameter") shouldBeEqualTo true
            hasName("otherParameter") shouldBeEqualTo false
            hasName("sampleparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koargument/snippet/forkonameprovider/", fileName)
}
