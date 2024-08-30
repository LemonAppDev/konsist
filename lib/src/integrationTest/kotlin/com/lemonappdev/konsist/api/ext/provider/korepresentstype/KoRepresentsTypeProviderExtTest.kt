package com.lemonappdev.konsist.api.ext.provider.korepresentstype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.provider.representsTypeOf
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleObject
import com.lemonappdev.konsist.testdata.SampleType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoRepresentsTypeProviderExtTest {
    @Test
    fun `parameter-represents-type-of-type`() {
        // given
        val sut =
            getSnippetFile("parameter-represents-type-of-type")
                .functions()
                .parameters
                .first()

        // then
        sut.representsTypeOf<SampleType>() shouldBeEqualTo true
    }

    @Test
    fun `annotation-represents-type`() {
        // given
        val sut =
            getSnippetFile("annotation-represents-type")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleAnnotation>() shouldBeEqualTo true
            representsTypeOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `annotation-represents-type-without-import`() {
        // given
        val sut =
            getSnippetFile("annotation-represents-type-without-import")
                .functions()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<Suppress>() shouldBeEqualTo true
            representsTypeOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `import-represents-complex-type`() {
        // given
        val sut =
            getSnippetFile("import-represents-complex-type")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleClass>() shouldBeEqualTo true
            representsTypeOf<String>() shouldBeEqualTo false
        }
    }

    @Test
    fun `import-represents-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("import-represents-kotlin-type")
                .imports
                .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<String>() shouldBeEqualTo true
            representsTypeOf<SampleType>() shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-represents-type`(
        fileName: String,
        valueForSampleClass: Boolean,
        valueForSampleInterface: Boolean,
        valueForSampleObject: Boolean,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .declarations(includeNested = true)
                .filterIsInstance<KoRepresentsTypeProvider>()
                .first()

        // then
        assertSoftly(sut) {
            representsTypeOf<SampleClass>() shouldBeEqualTo valueForSampleClass
            representsTypeOf<SampleInterface>() shouldBeEqualTo valueForSampleInterface
            representsTypeOf<SampleObject>() shouldBeEqualTo valueForSampleObject
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("api/ext/provider/korepresentstype/snippet/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("class-represents-type", true, false, false),
                arguments("interface-represents-type", false, true, false),
                arguments("object-represents-type", false, false, true),
            )
    }
}
