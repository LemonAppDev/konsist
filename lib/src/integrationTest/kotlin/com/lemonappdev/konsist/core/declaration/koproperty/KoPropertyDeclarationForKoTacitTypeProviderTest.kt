package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.properties
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClassWithParameter
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoTacitTypeProviderTest {
    @Test
    fun `property-has-no-tacit-type`() {
        // given
        val sut =
            getSnippetFile("property-has-no-tacit-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitType("String") shouldBeEqualTo false
            hasTacitTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-simple-type`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-simple-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitType("String") shouldBeEqualTo true
            hasTacitType("Int") shouldBeEqualTo false
            hasTacitTypeOf(String::class) shouldBeEqualTo true
            hasTacitTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-complex-type`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-complex-type")
                .properties()
                .first { it.name == "sampleProperty" }

        // then
        assertSoftly(sut) {
            hasTacitType("SampleClassWithParameter") shouldBeEqualTo true
            hasTacitType("SampleClass") shouldBeEqualTo false
            hasTacitTypeOf(SampleClassWithParameter::class) shouldBeEqualTo true
            hasTacitTypeOf(SampleClass::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-implicit-complex-type`() {
        // given
        val sut =
            getSnippetFile("property-has-implicit-complex-type")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            hasTacitType("SampleClassWithParameter") shouldBeEqualTo true
            hasTacitType("SampleClass") shouldBeEqualTo false
            hasTacitTypeOf(SampleClassWithParameter::class) shouldBeEqualTo true
            hasTacitTypeOf(SampleClass::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkotacittypeprovider/", fileName)
}
