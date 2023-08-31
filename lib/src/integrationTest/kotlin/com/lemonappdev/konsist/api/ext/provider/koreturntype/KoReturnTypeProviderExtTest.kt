package com.lemonappdev.konsist.api.ext.provider.koreturntype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasReceiverTypeOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReceiverTag
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReturnTypeProviderExtTest {
    @Test
    fun `declaration-with-return-type-has-valid-kdoc-return-tag`() {
        // given
        val sut = getSnippetFile("declaration-with-return-type-has-valid-kdoc-return-tag")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-return-type-has-not-valid-kdoc-return-tag`() {
        // given
        val sut = getSnippetFile("declaration-with-return-type-has-not-valid-kdoc-return-tag")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-return-type-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-with-return-type-has-no-kdoc")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-unit-return-type-has-valid-kdoc-return-tag`() {
        // given
        val sut = getSnippetFile("declaration-with-unit-return-type-has-valid-kdoc-return-tag")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-unit-return-type-has-not-valid-kdoc-return-tag`() {
        // given
        val sut = getSnippetFile("declaration-with-unit-return-type-has-not-valid-kdoc-return-tag")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-unit-return-type-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-with-unit-return-type-has-no-kdoc")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-return-type-has-valid-kdoc-return-tag`() {
        // given
        val sut = getSnippetFile("declaration-without-return-type-has-valid-kdoc-return-tag")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-return-type-has-not-valid-kdoc-return-tag`() {
        // given
        val sut = getSnippetFile("declaration-without-return-type-has-not-valid-kdoc-return-tag")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-return-type-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-without-return-type-has-no-kdoc")
            .declarationsOf<KoReturnTypeProvider>()
            .first()

        // then
        sut.hasValidKDocReturnTag() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koreturntype/snippet/", fileName)
}
