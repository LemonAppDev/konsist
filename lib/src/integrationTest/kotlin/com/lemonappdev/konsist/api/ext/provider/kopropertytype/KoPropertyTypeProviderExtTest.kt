package com.lemonappdev.konsist.api.ext.provider.kopropertytype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoPropertyTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass
import hasTypeOf
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyTypeProviderExtTest {
    @Test
    fun `declaration-has-no-type`() {
        // given
        val sut = getSnippetFile("declaration-has-no-type")
            .declarationsOf<KoPropertyTypeProvider>()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeOf<Int>() shouldBeEqualTo false
            hasTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-simple-type`() {
        // given
        val sut = getSnippetFile("declaration-has-simple-type")
            .declarationsOf<KoPropertyTypeProvider>()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeOf<Int>() shouldBeEqualTo true
            hasTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-complex-type`() {
        // given
        val sut = getSnippetFile("declaration-has-complex-type")
            .declarationsOf<KoPropertyTypeProvider>()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeOf<SampleClass>() shouldBeEqualTo true
            hasTypeOf<Int>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/kopropertytype/snippet/", fileName)
}
