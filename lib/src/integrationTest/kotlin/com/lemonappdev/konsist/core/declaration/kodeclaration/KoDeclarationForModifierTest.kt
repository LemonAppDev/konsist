package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.COMPANION
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.INLINE
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.OPERATOR
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForModifierTest {
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
            .first { it.name == declarationName }

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTypeAliasModifiers")
    fun `typealias-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForPrimaryConstructorModifiers")
    fun `primary-constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForSecondaryConstructorModifiers")
    fun `secondary-constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasModifiersWithoutParameter")
    fun `declaration-with-modifier-without-parameter`(
        fileName: String,
        declarationName: String,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasModifiers() shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasModifiersWithOneParameter")
    fun `declaration-with-modifier-with-one-parameter`(
        fileName: String,
        declarationName: String,
        modifier: KoModifier,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasModifiers(modifier) shouldBeEqualTo value
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationHasModifiersWithTwoParameters")
    fun `declaration-with-modifier-with-two-parameters`(
        fileName: String,
        declarationName: String,
        modifier1: KoModifier,
        modifier2: KoModifier,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        sut.hasModifiers(modifier1, modifier2) shouldBeEqualTo value
    }

    @Test
    fun `typealias-has-public-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-public-modifier")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("typealias-has-no-modifiers")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/formodifier/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationModifiers() = listOf(
            arguments("class-has-modifiers", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotation-with-parameter", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotation-without-parameter", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotations", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("companion-object-has-modifiers", "SampleCompanionObject", listOf(PROTECTED, FINAL, COMPANION)),
            arguments(
                "companion-object-has-modifiers-and-annotation-with-parameter",
                "SampleCompanionObject",
                listOf(PROTECTED, FINAL, COMPANION),
            ),
            arguments(
                "companion-object-has-modifiers-and-annotation-without-parameter",
                "SampleCompanionObject",
                listOf(PROTECTED, FINAL, COMPANION),
            ),
            arguments("companion-object-has-modifiers-and-annotations", "SampleCompanionObject", listOf(PROTECTED, FINAL, COMPANION)),
            arguments("function-has-modifiers", "invoke", listOf(PROTECTED, OPEN, SUSPEND, INLINE, OPERATOR)),
            arguments("function-has-modifiers-and-annotation-with-parameter", "invoke", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotation-without-parameter", "invoke", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotations", "invoke", listOf(PROTECTED, OPEN)),
            arguments("interface-has-modifiers", "SampleInterface", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-and-annotation-with-parameter", "SampleInterface", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-and-annotation-without-parameter", "SampleInterface", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-and-annotations", "SampleInterface", listOf(PUBLIC, ABSTRACT)),
            arguments("object-has-modifiers", "SampleObject", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotation-with-parameter", "SampleObject", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotation-without-parameter", "SampleObject", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotations", "SampleObject", listOf(PRIVATE, DATA)),
            arguments("property-has-modifiers", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-with-parameter", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-without-parameter", "sampleProperty", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotations", "sampleProperty", listOf(PROTECTED, OPEN)),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTypeAliasModifiers() = listOf(
            arguments("typealias-has-modifier"),
            arguments("typealias-has-modifiers-and-annotation-with-parameter"),
            arguments("typealias-has-modifiers-and-annotation-without-parameter"),
            arguments("typealias-has-modifiers-and-annotations"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForPrimaryConstructorModifiers() = listOf(
            arguments("primary-constructor-has-modifier"),
            arguments("primary-constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("primary-constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("primary-constructor-has-modifiers-and-annotations"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSecondaryConstructorModifiers() = listOf(
            arguments("secondary-constructor-has-modifier"),
            arguments("secondary-constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotations"),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasModifiersWithoutParameter() = listOf(
            arguments("class-has-public-modifier", "SampleClass", true),
            arguments("class-has-no-modifier", "SampleClass", false),
            arguments("companion-object-has-public-modifier", "SampleCompanionObject", true),
            arguments("companion-object-has-no-modifiers", "SampleCompanionObject", true),
            arguments("function-has-public-modifier", "sampleFunction", true),
            arguments("function-has-no-modifier", "sampleFunction", false),
            arguments("interface-has-public-modifier", "SampleInterface", true),
            arguments("interface-has-no-modifiers", "SampleInterface", false),
            arguments("object-has-public-modifier", "SampleObject", true),
            arguments("object-has-no-modifiers", "SampleObject", false),
            arguments("property-has-public-modifier", "sampleProperty", true),
            arguments("property-has-no-modifier", "sampleProperty", false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasModifiersWithOneParameter() = listOf(
            arguments("class-has-no-modifier", "SampleClass", PUBLIC, false),
            arguments("class-has-public-modifier", "SampleClass", PUBLIC, true),
            arguments("class-has-public-modifier", "SampleClass", PRIVATE, false),
            arguments("class-has-two-modifiers", "SampleClass", PRIVATE, true),
            arguments("class-has-two-modifiers", "SampleClass", OPEN, true),
            arguments("class-has-two-modifiers", "SampleClass", PROTECTED, false),
            arguments("companion-object-has-no-modifiers", "SampleCompanionObject", PUBLIC, false),
            arguments("companion-object-has-public-modifier", "SampleCompanionObject", PUBLIC, true),
            arguments("companion-object-has-public-modifier", "SampleCompanionObject", PRIVATE, false),
            arguments("companion-object-has-two-modifiers", "SampleCompanionObject", PROTECTED, true),
            arguments("companion-object-has-two-modifiers", "SampleCompanionObject", FINAL, true),
            arguments("companion-object-has-two-modifiers", "SampleCompanionObject", PRIVATE, false),
            arguments("function-has-no-modifier", "sampleFunction", PUBLIC, false),
            arguments("function-has-public-modifier", "sampleFunction", PUBLIC, true),
            arguments("function-has-public-modifier", "sampleFunction", PRIVATE, false),
            arguments("function-has-two-modifiers", "sampleFunction", PROTECTED, true),
            arguments("function-has-two-modifiers", "sampleFunction", SUSPEND, true),
            arguments("function-has-two-modifiers", "sampleFunction", PRIVATE, false),
            arguments("interface-has-no-modifiers", "SampleInterface", PUBLIC, false),
            arguments("interface-has-public-modifier", "SampleInterface", PUBLIC, true),
            arguments("interface-has-public-modifier", "SampleInterface", PRIVATE, false),
            arguments("interface-has-two-modifiers", "SampleInterface", ABSTRACT, true),
            arguments("interface-has-two-modifiers", "SampleInterface", PUBLIC, true),
            arguments("interface-has-two-modifiers", "SampleInterface", PRIVATE, false),
            arguments("object-has-no-modifiers", "SampleObject", PUBLIC, false),
            arguments("object-has-public-modifier", "SampleObject", PUBLIC, true),
            arguments("object-has-public-modifier", "SampleObject", PRIVATE, false),
            arguments("object-has-two-modifiers", "SampleObject", PRIVATE, true),
            arguments("object-has-two-modifiers", "SampleObject", DATA, true),
            arguments("object-has-two-modifiers", "SampleObject", PROTECTED, false),
            arguments("property-has-no-modifier", "sampleProperty", PUBLIC, false),
            arguments("property-has-public-modifier", "sampleProperty", PUBLIC, true),
            arguments("property-has-public-modifier", "sampleProperty", PRIVATE, false),
            arguments("property-has-two-modifiers", "sampleProperty", PROTECTED, true),
            arguments("property-has-two-modifiers", "sampleProperty", OPEN, true),
            arguments("property-has-two-modifiers", "sampleProperty", PRIVATE, false),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationHasModifiersWithTwoParameters() = listOf(
            arguments("class-has-two-modifiers", "SampleClass", PRIVATE, OPEN, true),
            arguments("class-has-two-modifiers", "SampleClass", OPEN, PRIVATE, true),
            arguments("class-has-two-modifiers", "SampleClass", PROTECTED, OPEN, false),
            arguments("companion-object-has-two-modifiers", "SampleCompanionObject", PROTECTED, FINAL, true),
            arguments("companion-object-has-two-modifiers", "SampleCompanionObject", FINAL, PROTECTED, true),
            arguments("companion-object-has-two-modifiers", "SampleCompanionObject", PRIVATE, FINAL, false),
            arguments("function-has-two-modifiers", "sampleFunction", PROTECTED, SUSPEND, true),
            arguments("function-has-two-modifiers", "sampleFunction", SUSPEND, PROTECTED, true),
            arguments("function-has-two-modifiers", "sampleFunction", PRIVATE, SUSPEND, false),
            arguments("interface-has-two-modifiers", "SampleInterface", ABSTRACT, PUBLIC, true),
            arguments("interface-has-two-modifiers", "SampleInterface", PUBLIC, ABSTRACT, true),
            arguments("interface-has-two-modifiers", "SampleInterface", PRIVATE, ABSTRACT, false),
            arguments("object-has-two-modifiers", "SampleObject", PRIVATE, DATA, true),
            arguments("object-has-two-modifiers", "SampleObject", DATA, PRIVATE, true),
            arguments("object-has-two-modifiers", "SampleObject", PROTECTED, DATA, false),
            arguments("property-has-two-modifiers", "sampleProperty", PROTECTED, OPEN, true),
            arguments("property-has-two-modifiers", "sampleProperty", OPEN, PROTECTED, true),
            arguments("property-has-two-modifiers", "sampleProperty", PRIVATE, OPEN, false),
        )
    }
}
