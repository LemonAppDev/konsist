package com.lemonappdev.konsist.core.declaration.koannotationdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import sun.jvm.hotspot.oops.CellTypeState.value
import kotlin.reflect.KClass

class KoAnnotationDeclarationForRepresentsTypeTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `annotation-represents-type`(
        type: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile("annotation-represents-type")
            .functions()
            .first()
            .annotations
            .first()

        // then
        sut.representsType(type) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotationdeclaration/snippet/forrepresentstype/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("SampleAnnotation", true),
            arguments("OtherAnnotation", false),
            arguments("com.lemonappdev.konsist.testdata.SampleAnnotation", true),
            arguments("com.lemonappdev.konsist.testdata.OtherAnnotation", false),
        )
    }
}
