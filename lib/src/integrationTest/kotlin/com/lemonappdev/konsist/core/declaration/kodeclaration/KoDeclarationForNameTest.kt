package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForNameTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-fully-qualified-name`(
        fileName: String,
        declarationName: String,
        value: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.fullyQualifiedName shouldBeEqualTo value
    }

    @Test
    fun `primary-constructor`() {
        // given
        val sut = getSnippetFile("primary-constructor")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `secondary-constructor`() {
        // given
        val sut = getSnippetFile("secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forname/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-fully-qualified-name", "SampleClass", "com.samplepackage.SampleClass"),
            arguments("function-has-fully-qualified-name", "sampleFunction", "com.samplepackage.sampleFunction"),
            arguments("interface-has-fully-qualified-name", "SampleInterface", "com.samplepackage.SampleInterface"),
            arguments("object-has-fully-qualified-name", "SampleObject", "com.samplepackage.SampleObject"),
            arguments("property-has-fully-qualified-name", "sampleProperty", "com.samplepackage.sampleProperty"),
            arguments("typealias-has-fully-qualified-name", "SampleTypeAlias", "com.samplepackage.SampleTypeAlias"),
        )
    }
}
