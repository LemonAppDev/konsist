package com.lemonappdev.konsist.api.ext.provider.koannotation

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.secondaryConstructors
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoAnnotationProviderExtTest {
    @Test
    fun `file-has-two-annotations-of-type`() {
        // given
        val sut =
            getSnippetFile("file-has-two-annotations-of-type")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-suppress-annotation-without-import`() {
        // given
        val sut =
            getSnippetFile("file-has-suppress-annotation-without-import")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<Suppress>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForAnnotationWithImport")
    fun `declaration-has-two-annotations-of-type`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .declarations(includeNested = true)
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoAnnotationProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForAnnotationWithoutImport")
    fun `declaration-has-suppress-annotation-without-import`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .declarations(includeNested = true)
                .filterNot { it is KoFileDeclaration }
                .filterIsInstance<KoAnnotationProvider>()
                .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<Suppress>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-two-annotations-of-type`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-has-two-annotations-of-type")
                .classes()
                .first()
                .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            it?.hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            it?.hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-suppress-annotation-without-import`() {
        // given
        val sut =
            getSnippetFile("primary-constructor-has-suppress-annotation-without-import")
                .classes()
                .first()
                .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.hasAnnotationOf<Suppress>() shouldBeEqualTo true
            it?.hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-two-annotations-of-type`() {
        // given
        val sut =
            getSnippetFile("secondary-constructor-has-two-annotations-of-type")
                .classes()
                .secondaryConstructors
                .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<SampleAnnotation1>() shouldBeEqualTo true
            hasAnnotationOf<SampleAnnotation2>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-suppress-annotation-without-import`() {
        // given
        val sut =
            getSnippetFile("secondary-constructor-has-suppress-annotation-without-import")
                .classes()
                .secondaryConstructors
                .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<Suppress>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("api/ext/provider/koannotation/snippet/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAnnotationWithImport() =
            listOf(
                arguments("class-has-two-annotations-of-type"),
                arguments("function-has-two-annotations-of-type"),
                arguments("interface-has-two-annotations-of-type"),
                arguments("object-has-two-annotations-of-type"),
                arguments("property-has-two-annotations-of-type"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAnnotationWithoutImport() =
            listOf(
                arguments("class-has-suppress-annotation-without-import"),
                arguments("function-has-suppress-annotation-without-import"),
                arguments("interface-has-suppress-annotation-without-import"),
                arguments("object-has-suppress-annotation-without-import"),
                arguments("property-has-suppress-annotation-without-import"),
            )
    }
}
