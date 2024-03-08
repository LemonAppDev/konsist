package com.lemonappdev.konsist.api.ext.provider.koreturntype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasReturnTypeOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReturnProviderExtTest {
    @Test
    fun `declaration-has-no-return-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-no-return-type")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasReturnTypeOf<String>() shouldBeEqualTo false
            hasReturnTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-return-type-with-simple-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-return-type-with-simple-type")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasReturnTypeOf<String>() shouldBeEqualTo true
            hasReturnTypeOf<Int>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-return-type-with-complex-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-return-type-with-complex-type")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasReturnTypeOf<SampleClass>() shouldBeEqualTo true
            hasReturnTypeOf<Int>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-with-return-type-has-valid-kdoc-return-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-return-type-has-valid-kdoc-return-tag")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-return-type-has-not-valid-kdoc-return-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-return-type-has-not-valid-kdoc-return-tag")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-return-type-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-with-return-type-has-no-kdoc")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-unit-return-type-has-valid-kdoc-return-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-unit-return-type-has-valid-kdoc-return-tag")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-unit-return-type-has-not-valid-kdoc-return-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-unit-return-type-has-not-valid-kdoc-return-tag")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-unit-return-type-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-with-unit-return-type-has-no-kdoc")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-return-type-has-valid-kdoc-return-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-without-return-type-has-valid-kdoc-return-tag")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-return-type-has-not-valid-kdoc-return-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-without-return-type-has-not-valid-kdoc-return-tag")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-return-type-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-without-return-type-has-no-kdoc")
                .declarationsOf<KoReturnProvider>()
                .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("api/ext/provider/koreturntype/snippet/", fileName)
}
