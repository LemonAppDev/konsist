package com.lemonappdev.konsist.api.ext.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationExtTest {
    @ParameterizedTest
    @MethodSource("provideValuesForAnnotationWithImport")
    fun `declaration-has-two-annotations-of-type`(
        fileName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
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
    fun `declaration-has-suppress-annotation-without-import`(
        fileName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
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
        val sut = getSnippetFile("primary-constructor-has-two-annotations-of-type")
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
        val sut = getSnippetFile("primary-constructor-has-suppress-annotation-without-import")
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
        val sut = getSnippetFile("secondary-constructor-has-two-annotations-of-type")
            .classes()
            .first()
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
        val sut = getSnippetFile("secondary-constructor-has-suppress-annotation-without-import")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            hasAnnotationOf<Suppress>() shouldBeEqualTo true
            hasAnnotationOf<NonExistingAnnotation>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/declaration/kodeclaration/snippet/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAnnotationWithImport() = listOf(
            arguments("class-has-two-annotations-of-type"),
            arguments("function-has-two-annotations-of-type"),
            arguments("interface-has-two-annotations-of-type"),
            arguments("object-has-two-annotations-of-type"),
            arguments("property-has-two-annotations-of-type"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAnnotationWithoutImport() = listOf(
            arguments("class-has-suppress-annotation-without-import"),
            arguments("function-has-suppress-annotation-without-import"),
            arguments("interface-has-suppress-annotation-without-import"),
            arguments("object-has-suppress-annotation-without-import"),
            arguments("property-has-suppress-annotation-without-import"),
        )
    }
}
