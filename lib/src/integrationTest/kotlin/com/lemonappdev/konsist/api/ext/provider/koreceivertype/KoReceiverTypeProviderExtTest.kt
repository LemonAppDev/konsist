package com.lemonappdev.konsist.api.ext.provider.koreceivertype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasReceiverTypeOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReceiverTag
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.testdata.FixtureClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReceiverTypeProviderExtTest {
    @Test
    fun `declaration-has-no-receiver-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-no-receiver-type")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasReceiverTypeOf<Int>() shouldBeEqualTo false
            hasReceiverTypeOf<FixtureClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-receiver-with-simple-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-receiver-with-simple-type")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasReceiverTypeOf<Int>() shouldBeEqualTo true
            hasReceiverTypeOf<FixtureClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-receiver-with-complex-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-receiver-with-complex-type")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasReceiverTypeOf<FixtureClass>() shouldBeEqualTo true
            hasReceiverTypeOf<Int>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-with-receiver-has-valid-kdoc-receiver-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-receiver-has-valid-kdoc-receiver-tag")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        sut.hasValidKDocReceiverTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-with-receiver-has-not-valid-kdoc-receiver-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-with-receiver-has-not-valid-kdoc-receiver-tag")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        sut.hasValidKDocReceiverTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-with-receiver-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-with-receiver-has-no-kdoc")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        sut.hasValidKDocReceiverTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-receiver-has-valid-kdoc-receiver-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-without-receiver-has-valid-kdoc-receiver-tag")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        sut.hasValidKDocReceiverTag() shouldBeEqualTo true
    }

    @Test
    fun `declaration-without-receiver-has-not-valid-kdoc-receiver-tag`() {
        // given
        val sut =
            getSnippetFile("declaration-without-receiver-has-not-valid-kdoc-receiver-tag")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        sut.hasValidKDocReceiverTag() shouldBeEqualTo false
    }

    @Test
    fun `declaration-without-receiver-has-no-kdoc`() {
        // given
        val sut =
            getSnippetFile("declaration-without-receiver-has-no-kdoc")
                .declarationsOf<KoReceiverTypeProvider>()
                .first()

        // then
        sut.hasValidKDocReceiverTag() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koreceivertype/snippet/", fileName)
}
