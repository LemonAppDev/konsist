package com.lemonappdev.konsist.core.declaration.kodeclaration.snippet

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForVisibilityModifierTest {

    @ParameterizedTest
    @MethodSource("provideValuesForVisibilityModifiers")
    fun `visibility-modifiers`(
        fileName: String,
        declarationName: String,
        isPublicByDefault: Boolean,
        isPublic: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isInternal: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo isPublicByDefault
            hasPublicModifier() shouldBeEqualTo isPublic
            hasPrivateModifier() shouldBeEqualTo isPrivate
            hasProtectedModifier() shouldBeEqualTo isProtected
            hasInternalModifier() shouldBeEqualTo isInternal
        }
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForVisibilityModifiers() = listOf(
            arguments("class-has-no-visibility-modifier", "SampleClass", true, false, false, false, false),
            arguments("class-has-public-visibility-modifier", "SampleClass", true, true, false, false, false),
            arguments("class-has-private-visibility-modifier", "SampleClass", false, false, true, false, false),
            arguments("class-has-protected-visibility-modifier", "SampleClass", false, false, false, true, false),
            arguments("class-has-internal-visibility-modifier", "SampleClass", false, false, false, false, true),
            arguments("companion-object-has-no-visibility-modifier", "SampleCompanionObject", true, false, false, false, false),
            arguments("companion-object-has-public-visibility-modifier", "SampleCompanionObject", true, true, false, false, false),
            arguments("companion-object-has-private-visibility-modifier", "SampleCompanionObject", false, false, true, false, false),
            arguments("companion-object-has-protected-visibility-modifier", "SampleCompanionObject", false, false, false, true, false),
            arguments("companion-object-has-internal-visibility-modifier", "SampleCompanionObject", false, false, false, false, true),
            arguments("function-has-no-visibility-modifier", "sampleFunction", true, false, false, false, false),
            arguments("function-has-public-visibility-modifier", "sampleFunction", true, true, false, false, false),
            arguments("function-has-private-visibility-modifier", "sampleFunction", false, false, true, false, false),
            arguments("function-has-protected-visibility-modifier", "sampleFunction", false, false, false, true, false),
            arguments("function-has-internal-visibility-modifier", "sampleFunction", false, false, false, false, true),
            arguments("interface-has-no-visibility-modifier", "SampleInterface", true, false, false, false, false),
            arguments("interface-has-public-visibility-modifier", "SampleInterface", true, true, false, false, false),
            arguments("interface-has-private-visibility-modifier", "SampleInterface", false, false, true, false, false),
            arguments("interface-has-protected-visibility-modifier", "SampleInterface", false, false, false, true, false),
            arguments("interface-has-internal-visibility-modifier", "SampleInterface", false, false, false, false, true),
            arguments("object-has-no-visibility-modifier", "SampleObject", true, false, false, false, false),
            arguments("object-has-public-visibility-modifier", "SampleObject", true, true, false, false, false),
            arguments("object-has-private-visibility-modifier", "SampleObject", false, false, true, false, false),
            arguments("object-has-protected-visibility-modifier", "SampleObject", false, false, false, true, false),
            arguments("object-has-internal-visibility-modifier", "SampleObject", false, false, false, false, true),
            arguments("property-has-no-visibility-modifier", "sampleProperty", true, false, false, false, false),
            arguments("property-has-public-visibility-modifier", "sampleProperty", true, true, false, false, false),
            arguments("property-has-private-visibility-modifier", "sampleProperty", false, false, true, false, false),
            arguments("property-has-protected-visibility-modifier", "sampleProperty", false, false, false, true, false),
            arguments("property-has-internal-visibility-modifier", "sampleProperty", false, false, false, false, true),
        )

    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forvisibilitymodifier/", fileName)
}
