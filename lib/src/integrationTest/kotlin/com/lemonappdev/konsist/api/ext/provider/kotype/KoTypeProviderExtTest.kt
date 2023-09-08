package com.lemonappdev.konsist.api.ext.provider.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoPropertyTypeProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleType
import hasTypeOf
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeProviderExtTest {
    @Test
    fun `declaration-has-simple-type`() {
        // given
        val sut = getSnippetFile("declaration-has-simple-type")
            .declarationsOf<KoTypeProvider>()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeOf<String>() shouldBeEqualTo true
            hasTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-complex-type`() {
        // given
        val sut = getSnippetFile("declaration-has-complex-type")
            .declarationsOf<KoTypeProvider>()
            .first()

        // then
        assertSoftly(sut) {
            hasTypeOf<SampleType>() shouldBeEqualTo true
            hasTypeOf<Int>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/kotype/snippet/", fileName)
}
