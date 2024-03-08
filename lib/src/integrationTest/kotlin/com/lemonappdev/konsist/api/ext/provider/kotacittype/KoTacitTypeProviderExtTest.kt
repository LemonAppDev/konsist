package com.lemonappdev.konsist.api.ext.provider.kotacittype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClassWithParameter
import hasTacitTypeOf
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTacitTypeProviderExtTest {
    @Test
    fun `declaration-has-no-tacit-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-no-tacit-type")
                .declarationsOf<KoTacitTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitTypeOf<Int>() shouldBeEqualTo false
            hasTacitTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-explicit-simple-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-explicit-simple-type")
                .declarationsOf<KoTacitTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitTypeOf<String>() shouldBeEqualTo true
            hasTacitTypeOf<Int>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-explicit-complex-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-explicit-complex-type")
                .declarationsOf<KoTacitTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitTypeOf<SampleClassWithParameter>() shouldBeEqualTo true
            hasTacitTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `declaration-has-implicit-complex-type`() {
        // given
        val sut =
            getSnippetFile("declaration-has-implicit-complex-type")
                .declarationsOf<KoTacitTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitTypeOf<SampleClassWithParameter>() shouldBeEqualTo true
            hasTacitTypeOf<SampleClass>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("api/ext/provider/kotacittype/snippet/", fileName)
}
