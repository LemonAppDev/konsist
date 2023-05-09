package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForIsTopLevelTest {
    @Suppress("detekt.LongParameterList")
    @ParameterizedTest
    @MethodSource("provideValuesForDeclarations")
    fun `declaration-is-top-level`(
        fileName: String,
        declarationName: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.isTopLevel() shouldBeEqualTo value
    }

    @Test
    fun `typealias-is-top-level`() {
        // given
        val sut = getSnippetFile("typealias-is-top-level")
            .typeAliases()
            .first()

        // then
        sut.isTopLevel() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/foristoplevel/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarations() = listOf(
            arguments("class-is-top-level", "SampleClass", true),
            arguments("class-is-not-top-level", "SampleNestedClass", false),
            arguments("companion-object-is-top-level", "SampleCompanionObject", true),
            arguments("companion-object-is-not-top-level", "SampleNestedCompanionObject", false),
            arguments("function-is-top-level", "sampleFunction", true),
            arguments("function-is-not-top-level", "sampleNestedFunction", false),
            arguments("interface-is-top-level", "SampleInterface", true),
            arguments("interface-is-not-top-level", "SampleNestedInterface", false),
            arguments("object-is-top-level", "SampleObject", true),
            arguments("object-is-not-top-level", "SampleNestedObject", false),
            arguments("property-is-top-level", "sampleProperty", true),
            arguments("property-is-not-top-level", "sampleNestedProperty", false),
        )
    }
}
