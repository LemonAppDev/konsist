package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClassWithParameter
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoTacitTypeProviderTest {
    @Test
    fun `variable-has-no-tacit-type`() {
        // given
        val sut = getSnippetFile("variable-has-no-tacit-type")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            hasTacitType("String") shouldBeEqualTo false
            hasTacitTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-has-explicit-simple-type`() {
        // given
        val sut = getSnippetFile("variable-has-explicit-simple-type")
            .functions()
            .variables
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
    fun `variable-has-explicit-complex-type`() {
        // given
        val sut = getSnippetFile("variable-has-explicit-complex-type")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            hasTacitType("SampleClassWithParameter") shouldBeEqualTo true
            hasTacitType("SampleClass") shouldBeEqualTo false
            hasTacitTypeOf(SampleClassWithParameter::class) shouldBeEqualTo true
            hasTacitTypeOf(SampleClass::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-has-implicit-complex-type`() {
        // given
        val sut = getSnippetFile("variable-has-implicit-complex-type")
            .functions()
            .variables
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kovariable/snippet/forkotacittypeprovider/", fileName)
}
