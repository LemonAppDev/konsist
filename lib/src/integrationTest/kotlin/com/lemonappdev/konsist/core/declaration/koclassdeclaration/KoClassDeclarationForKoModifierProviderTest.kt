package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForKoModifierProviderTest {
    @Test
    fun `class-without-modifiers`() {
        // given
        val sut = getSnippetFile("class-without-modifiers")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            modifiers.toList() shouldBeEqualTo emptyList()
            hasModifiers() shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA) shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            isPublicOrDefault() shouldBeEqualTo true
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
            hasEnumModifier() shouldBeEqualTo false
            hasSealedModifier() shouldBeEqualTo false
            hasInnerModifier() shouldBeEqualTo false
            hasValueModifier() shouldBeEqualTo false
            hasAnnotationModifier() shouldBeEqualTo false
            hasDataModifier() shouldBeEqualTo false
            hasActualModifier() shouldBeEqualTo false
            hasExpectModifier() shouldBeEqualTo false
            hasAbstractModifier() shouldBeEqualTo false
            hasOpenModifier() shouldBeEqualTo false
            hasFinalModifier() shouldBeEqualTo false
            hasVarargModifier() shouldBeEqualTo false
            hasNoInlineModifier() shouldBeEqualTo false
            hasCrossInlineModifier() shouldBeEqualTo false
            hasOperatorModifier() shouldBeEqualTo false
            hasInlineModifier() shouldBeEqualTo false
            hasTailrecModifier() shouldBeEqualTo false
            hasInfixModifier() shouldBeEqualTo false
            hasExternalModifier() shouldBeEqualTo false
            hasSuspendModifier() shouldBeEqualTo false
            hasOverrideModifier() shouldBeEqualTo false
            hasFunModifier() shouldBeEqualTo false
            hasLateinitModifier() shouldBeEqualTo false
            hasConstModifier() shouldBeEqualTo false
            hasCompanionModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-private-and-open-modifiers`() {
        // given
        val sut = getSnippetFile("class-with-private-and-open-modifiers")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForDeclarationModifiers")
    fun `declaration-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()

        // then
        sut.modifiers.toList() shouldBeEqualTo modifiers
    }

    @Test
    fun `public-class`() {
        // given
        val sut = getSnippetFile("public-class")
            .classes()
            .first()

        // then
        sut.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `public-by-default-class`() {
        // given
        val sut = getSnippetFile("public-by-default-class")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `private-class`() {
        // given
        val sut = getSnippetFile("private-class")
            .classes()
            .first()

        // then
        sut.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `protected-class`() {
        // given
        val sut = getSnippetFile("protected-class")
            .classes()
            .flatMap { it.classes() }
            .first()

        // then
        sut.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `internal-class`() {
        // given
        val sut = getSnippetFile("internal-class")
            .classes()
            .first()

        // then
        sut.hasInternalModifier() shouldBeEqualTo true
    }

    @Test
    fun `abstract-class`() {
        // given
        val sut = getSnippetFile("abstract-class")
            .classes()
            .first()

        // then
        sut.hasAbstractModifier() shouldBeEqualTo true
    }

    @Test
    fun `annotation-class`() {
        // given
        val sut = getSnippetFile("annotation-class")
            .classes()
            .first()

        // then
        sut.hasAnnotationModifier() shouldBeEqualTo true
    }

    @Test
    fun `data-class`() {
        // given
        val sut = getSnippetFile("data-class")
            .classes()
            .first()

        // then
        sut.hasDataModifier() shouldBeEqualTo true
    }

    @Test
    fun `enum-class`() {
        // given
        val sut = getSnippetFile("enum-class")
            .classes()
            .first()

        // then
        sut.hasEnumModifier() shouldBeEqualTo true
    }

    @Test
    fun `sealed-class`() {
        // given
        val sut = getSnippetFile("sealed-class")
            .classes()
            .first()

        // then
        sut.hasSealedModifier() shouldBeEqualTo true
    }

    @Test
    fun `value-class`() {
        // given
        val sut = getSnippetFile("value-class")
            .classes()
            .first()

        // then
        sut.hasValueModifier() shouldBeEqualTo true
    }

    @Test
    fun `open-class`() {
        // given
        val sut = getSnippetFile("open-class")
            .classes()
            .first()

        // then
        sut.hasOpenModifier() shouldBeEqualTo true
    }

    @Test
    fun `final-class`() {
        // given
        val sut = getSnippetFile("final-class")
            .classes()
            .first()

        // then
        sut.hasFinalModifier() shouldBeEqualTo true
    }

    @Test
    fun `nested-inner-class`() {
        // given
        val sut = getSnippetFile("nested-inner-class")
            .classes(includeNested = true)
            .first { it.name == "InnerClass" }

        // then
        sut.hasInnerModifier() shouldBeEqualTo true
    }

    @Test
    fun `actual-class`() {
        // given
        val sut = getSnippetFile("actual-class")
            .classes(includeNested = true)
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `expect-class`() {
        // given
        val sut = getSnippetFile("expect-class")
            .classes(includeNested = true)
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclarationModifiers() = listOf(
            arguments("class-has-modifiers", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotation-with-parameter", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotation-without-parameter", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-annotation-and-comment", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotations", "SampleClass", listOf(PRIVATE, OPEN)),
            arguments("class-has-modifiers-and-annotation-with-angle-brackets", "SampleClass", listOf(PRIVATE, OPEN)),
        )
    }
}
