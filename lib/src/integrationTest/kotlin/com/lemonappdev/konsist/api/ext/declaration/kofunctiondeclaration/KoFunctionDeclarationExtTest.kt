package com.lemonappdev.konsist.api.ext.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.api.ext.declaration.hasReceiverOf
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionDeclarationExtTest {
    @Test
    fun `function-has-receiver-with-simple-type`() {
        // given
        val sut = getSnippetFile("function-has-receiver-with-simple-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReceiverOf<Int>() shouldBeEqualTo true
            hasReceiverOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-receiver-with-complex-type`() {
        // given
        val sut = getSnippetFile("function-has-receiver-with-complex-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReceiverOf<SampleClass>() shouldBeEqualTo true
            hasReceiverOf<Int>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kofunctiondeclaration/snippet/", fileName)
}
