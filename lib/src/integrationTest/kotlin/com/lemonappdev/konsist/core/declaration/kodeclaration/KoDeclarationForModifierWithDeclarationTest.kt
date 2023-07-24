package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForModifierWithDeclarationTest {
    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationModifiers")
    fun `declaration-modifiers`(
        fileName: String,
        declarationName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .first { it.name == declarationName } as KoModifierProvider

        // then
        sut.modifiers.toList() shouldBeEqualTo modifiers
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasModifiersWithoutParameters")
    fun `declaration-has-protected-modifier`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .first { it.name == declarationName } as KoModifierProvider

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasModifiersWithOneParameter")
    fun `declaration-has-public-modifier`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .first { it.name == declarationName } as KoModifierProvider

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasTwoModifiers")
    fun `declaration-has-two-modifiers`(
        fileName: String,
        declarationName: String,
        modifier1: KoModifier,
        modifier2: KoModifier,
        modifier3: KoModifier,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .first { it.name == declarationName } as KoModifierProvider

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(modifier1) shouldBeEqualTo true
            hasModifiers(modifier2) shouldBeEqualTo true
            hasModifiers(modifier3) shouldBeEqualTo false
            hasModifiers(modifier1, modifier2) shouldBeEqualTo true
            hasModifiers(modifier2, modifier1) shouldBeEqualTo true
            hasModifiers(modifier3, modifier2) shouldBeEqualTo false
            hasModifiers(modifier3, modifier2, modifier1) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationWithoutModifiers")
    fun `declaration-has-no-modifiers`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .first { it.name == declarationName } as KoModifierProvider

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/formodifierwithdeclaration/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationModifiers() = listOf(
            arguments("property-has-modifiers", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-with-parameter", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-without-parameter", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-annotation-and-comment", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotations", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-with-angle-brackets", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("typealias-has-modifier", "SampleTypeAlias", listOf(PRIVATE)),
            arguments("typealias-has-modifiers-and-annotation-with-parameter", "SampleTypeAlias", listOf(PRIVATE)),
            arguments("typealias-has-modifiers-and-annotation-without-parameter", "SampleTypeAlias", listOf(PRIVATE)),
            arguments("typealias-has-modifiers-annotation-and-comment", "SampleTypeAlias", listOf(PRIVATE)),
            arguments("typealias-has-modifiers-and-annotations", "SampleTypeAlias", listOf(PRIVATE)),
            arguments("typealias-has-modifiers-and-annotation-with-angle-brackets", "SampleTypeAlias", listOf(PRIVATE)),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasModifiersWithoutParameters() = listOf(
            arguments("class-has-protected-modifier", "SampleClass"),
            arguments("function-has-protected-modifier", "sampleFunction"),
            arguments("interface-has-protected-modifier", "SampleInterface"),
            arguments("object-has-protected-modifier", "SampleObject"),
            arguments("property-has-protected-modifier", "sampleProperty"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasModifiersWithOneParameter() = listOf(
            arguments("class-has-public-modifier", "SampleClass"),
            arguments("function-has-public-modifier", "sampleFunction"),
            arguments("interface-has-public-modifier", "SampleInterface"),
            arguments("object-has-public-modifier", "SampleObject"),
            arguments("property-has-public-modifier", "sampleProperty"),
            arguments("typealias-has-public-modifier", "SampleTypeAlias"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasTwoModifiers() = listOf(
            arguments("class-has-two-modifiers", "SampleClass", PRIVATE, OPEN, PROTECTED),
            arguments("function-has-two-modifiers", "sampleFunction", PROTECTED, SUSPEND, PRIVATE),
            arguments("interface-has-two-modifiers", "SampleInterface", ABSTRACT, PUBLIC, PRIVATE),
            arguments("object-has-two-modifiers", "SampleObject", PRIVATE, DATA, PROTECTED),
            arguments("property-has-two-modifiers", "sampleProperty", PROTECTED, OPEN, PRIVATE),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationWithoutModifiers() = listOf(
            arguments("class-has-no-modifier", "SampleClass"),
            arguments("function-has-no-modifier", "sampleFunction"),
            arguments("interface-has-no-modifiers", "SampleInterface"),
            arguments("object-has-no-modifiers", "SampleObject"),
            arguments("property-has-no-modifier", "sampleProperty"),
            arguments("typealias-has-no-modifiers", "SampleTypeAlias"),
        )
    }
}
