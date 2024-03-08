package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoValueProviderTest {
    @Test
    fun `property-has-value`() {
        // given
        val sut =
            getSnippetFile("property-has-value")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "text"
            hasValue() shouldBeEqualTo true
            hasValue("text") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-inside-interface-has-no-value`() {
        // given
        val sut =
            getSnippetFile("property-inside-interface-has-no-value")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo null
            hasValue() shouldBeEqualTo false
            hasValue("0") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-getter-and-setter-has-value`() {
        // given
        val sut =
            getSnippetFile("property-with-getter-and-setter-has-value")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "0"
            hasValue() shouldBeEqualTo true
            hasValue("0") shouldBeEqualTo true
            hasValue("10") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-getter-and-setter-has-no-value`() {
        // given
        val sut =
            getSnippetFile("property-with-getter-and-setter-has-no-value")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo null
            hasValue() shouldBeEqualTo false
            hasValue("0") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-delegation-has-no-value`() {
        // given
        val sut =
            getSnippetFile("property-with-delegation-has-no-value")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo null
            hasValue() shouldBeEqualTo false
            hasValue("0") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkovalueprovider/", fileName)
}
