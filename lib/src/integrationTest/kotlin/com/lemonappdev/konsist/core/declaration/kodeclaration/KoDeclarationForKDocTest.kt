package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForKDocTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `declaration-kdoc`(
        fileName: String,
        declarationName: String,
        hasKDoc: Boolean,

    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasKDoc() shouldBeEqualTo hasKDoc
    }

    @Test
    fun `typealias-has-kdoc`() {
        // given
        val sut = getSnippetFile("typealias-has-kdoc")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-has-no-kdoc`() {
        // given
        val sut = getSnippetFile("typealias-has-no-kdoc")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forkdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-has-kdoc", "SampleClass", true),
            arguments("class-has-no-kdoc", "SampleClass", false),
            arguments("companion-object-has-kdoc", "SampleCompanionObject", true),
            arguments("companion-object-has-no-kdoc", "SampleCompanionObject", false),
            arguments("function-has-kdoc", "sampleFunction", true),
            arguments("function-has-no-kdoc", "sampleFunction", false),
            arguments("interface-has-kdoc", "SampleInterface", true),
            arguments("interface-has-no-kdoc", "SampleInterface", false),
            arguments("object-has-kdoc", "SampleObject", true),
            arguments("object-has-no-kdoc", "SampleObject", false),
            arguments("property-has-kdoc", "sampleProperty", true),
            arguments("property-has-no-kdoc", "sampleProperty", false),
        )
    }
}
