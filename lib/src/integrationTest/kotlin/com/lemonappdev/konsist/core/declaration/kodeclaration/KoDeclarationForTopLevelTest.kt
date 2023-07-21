package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForTopLevelTest {
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
            .filterIsInstance<KoNameProvider>()
            .first { it.name == declarationName } as KoTopLevelProvider

        // then
        sut.isTopLevel() shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/fortoplevel/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarations() = listOf(
            arguments("function-is-top-level", "sampleFunction", true),
            arguments("function-is-not-top-level", "sampleNestedFunction", false),
            arguments("interface-is-top-level", "SampleInterface", true),
            arguments("interface-is-not-top-level", "SampleNestedInterface", false),
            arguments("object-is-top-level", "SampleObject", true),
            arguments("object-is-not-top-level", "SampleNestedObject", false),
            arguments("property-is-top-level", "sampleProperty", true),
            arguments("property-is-not-top-level", "sampleNestedProperty", false),
            arguments("typealias-is-top-level", "SampleTypeAlias", true),
        )
    }
}
